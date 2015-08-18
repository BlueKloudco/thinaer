package com.kloudnation.thinaer.model;

/**
 * abstract base class for the possible OptionValues
 *
 * @param <T>
 */
public abstract class OptionValue<T> {

    protected OptionType optionType;
    protected byte[] byteArrayValue;

    public OptionValue( OptionType optionType, byte[] value ) {
        this.optionType = optionType;
        this.byteArrayValue = value;
    }

    public byte[] getByteArrayValue() {
        return byteArrayValue;
    }

    public abstract T getValue();
}
