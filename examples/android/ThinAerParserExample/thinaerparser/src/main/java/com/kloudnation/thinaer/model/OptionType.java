package com.kloudnation.thinaer.model;

/**
 * Enum for the various option types
 */
public enum OptionType {
    BATTERY( (byte) 0x01 ),
    TEMPERATURE( (byte) 0x02 ),
    TEMPERATURE_EXTERNAL( (byte) 0x03 ),
    HUMIDITY( (byte) 0x04 ),
    BOOLEAN( (byte) 0x05 )
    /* others as they become available */;

    private byte value;

    OptionType( byte optionType ) {
        value = optionType;
    }

    public byte getByteValue() {
        return value;
    }

    public static OptionType fromByte( byte optionType ) {
       for ( OptionType item : OptionType.values() ) {
            if ( optionType == item.getByteValue() ) {
                return item;
            }
        }
        return null;
    }
}
