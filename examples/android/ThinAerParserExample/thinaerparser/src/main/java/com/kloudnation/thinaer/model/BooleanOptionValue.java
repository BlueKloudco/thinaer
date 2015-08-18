package com.kloudnation.thinaer.model;

import com.kloudnation.thinaer.utils.ByteUtils;

/**
 * class representing an Option Value encapsulating a Boolean
 */
public class BooleanOptionValue
        extends OptionValue<Boolean> {

    public BooleanOptionValue( OptionType optionType, byte[] value ) {
        super( optionType, value );
    }

    @Override
    public Boolean getValue() {
        if(byteArrayValue == null ||
                byteArrayValue.length == 0) {
            return null;
        }
        int val = ByteUtils.intFromSingleByte( byteArrayValue[0] );
        return val != 0;
    }
}
