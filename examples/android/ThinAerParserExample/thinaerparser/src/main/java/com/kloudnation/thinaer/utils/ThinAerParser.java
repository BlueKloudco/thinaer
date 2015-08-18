package com.kloudnation.thinaer.utils;

import com.kloudnation.thinaer.model.BooleanOptionValue;
import com.kloudnation.thinaer.model.IntegerOptionValue;
import com.kloudnation.thinaer.model.OptionType;
import com.kloudnation.thinaer.model.OptionValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example class to parse the bluetooth LE scan record, check the Bluetooth SIG ID to see if it
 * matches BlueKloud's ThinAer spec, and if so, split it into its constituent parts
 */
public class ThinAerParser {

    private static final short BLUEKLOUDID = 496;

    int initialStructureSize;
    int advertisingPacketSize;
    int advertisingPacketStart;
    byte[] advertisingPacket;
    int manufacturerSpecificDataSize;
    int manufacturerSpecificDataStart;
    byte[] manufacturerSpecificData;
    short bluetoothSIGId;
    short customerId;
    byte[] beaconId;
    Map<OptionType, OptionValue> options = new HashMap<>(  );

    /**
     * Constructor accepting a byte array representing a bluetooth le scan record
     *
     * @param scanRecord
     */
    public ThinAerParser( final byte[] scanRecord ) {

        // retrieve start/lengths for scnrecord structures
        initialStructureSize = ByteUtils.intFromSingleByte( scanRecord[0] );
        advertisingPacketStart = initialStructureSize + 2;
        advertisingPacketSize = ByteUtils.intFromSingleByte( scanRecord[1 + initialStructureSize] );
        manufacturerSpecificDataSize = ByteUtils.intFromSingleByte( scanRecord[advertisingPacketStart + advertisingPacketSize] );
        manufacturerSpecificDataStart = advertisingPacketStart + advertisingPacketSize + 1;

        // extract primary advertising packet
        advertisingPacket = ByteUtils.subRange( scanRecord, advertisingPacketStart, advertisingPacketSize );

        // extract manufacturer specific data
        manufacturerSpecificData = ByteUtils.subRange( scanRecord, manufacturerSpecificDataStart, manufacturerSpecificDataSize );

        bluetoothSIGId = ByteUtils.byteArrayToShortLE( ByteUtils.subRange( advertisingPacket, 1, 2 ) );

        // only proceed if it matches the BlueKloud SIG
        if ( bluetoothSIGId == BLUEKLOUDID ) {

            // This is a ThinAer beacon, so get the company/customer id and the beacon id
            customerId = ByteUtils.byteArrayToShortLE( ByteUtils.subRange( advertisingPacket, 3, 2 ) );
            beaconId = ByteUtils.subRange( advertisingPacket, 5, 8 );

            // check for the presence of Options
            if ( advertisingPacketSize > 13 ) {

                // grab the entire array of option bytes
                byte[] optionBytes = ByteUtils.subRange( advertisingPacket, 13, advertisingPacketSize - 13 );

                // split the optionBytes on the Stop Byte (0xFF)
                List<byte[]> arrays = new ArrayList<>();
                int start = 0;
                for ( int x = 0; x < optionBytes.length; x++ ) {
                    if(optionBytes[x] == (byte) 0xFF) {
                        arrays.add( ByteUtils.subRange( optionBytes, start, x - start ) );
                        start = x + 1;
                    }
                }

                // iterate over all possible option KV pairs - first byte is the key, the rest is the value
                for(byte[] byteArray : arrays) {

                    // grab the first byte and cast to OptionType enum
                    OptionType optionType = OptionType.fromByte( byteArray[0] );

                    if( optionType != null ) {

                        // parse the remainder as the value, casting to the appropriate OptionValue
                        //  for the specific option type
                        OptionValue optionValue = null;

                        switch( optionType ) {
                            case BATTERY:
                            case TEMPERATURE:
                            case TEMPERATURE_EXTERNAL:
                            case HUMIDITY:
                                optionValue = new IntegerOptionValue(
                                        optionType,
                                        ByteUtils.subRange( byteArray, 1, byteArray.length - 1 )
                                );
                                break;

                            case BOOLEAN:
                                optionValue = new BooleanOptionValue(
                                        optionType,
                                        ByteUtils.subRange( byteArray, 1, byteArray.length - 1 )
                                );
                                break;

                            /* other types as they become available */

                            default:
                                // leave null
                        }

                        options.put( optionType, optionValue );
                    }
                }
            }
        }
    }

    //region Accessors

    public short getBluetoothSIGId() {
        return bluetoothSIGId;
    }

    public short getCustomerId() {
        return customerId;
    }

    public byte[] getBeaconId() {
        return beaconId;
    }

    public Map<OptionType, OptionValue> getOptions() {
        return options;
    }

    //endregion
}
