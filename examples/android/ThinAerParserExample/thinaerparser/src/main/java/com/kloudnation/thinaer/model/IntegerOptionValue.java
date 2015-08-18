package com.kloudnation.thinaer.model;

import com.kloudnation.thinaer.utils.ByteUtils;


/**
 * class representing an Option Value encapsulating an Integer
 * byte array must be up to 4 bytes long
 */
public class IntegerOptionValue
        extends OptionValue<Integer> {

    public IntegerOptionValue( OptionType optionType, byte[] value ) {
        super( optionType, value );
    }

    @Override
    public Integer getValue() {

        if ( byteArrayValue != null ) {

            final int len = byteArrayValue.length;
            byte[] data;

            switch ( len ) {

                case 1:
                    data = new byte[]{0x00, 0x00, 0x00, byteArrayValue[0]};
                    return ByteUtils.byteArrayToInt( data );

                case 2:
                    data = new byte[]{0x00, 0x00, byteArrayValue[0], byteArrayValue[1]};
                    return ByteUtils.byteArrayToInt( data );

                case 3:
                    data = new byte[]{0x00, byteArrayValue[0], byteArrayValue[1], byteArrayValue[2]};
                    return ByteUtils.byteArrayToInt( data );

                case 4:
                    return ByteUtils.byteArrayToInt( byteArrayValue );

                default:
                    return null;
            }
        }
        return null;
    }
}
