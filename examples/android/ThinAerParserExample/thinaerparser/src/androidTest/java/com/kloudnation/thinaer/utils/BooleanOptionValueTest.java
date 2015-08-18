package com.kloudnation.thinaer.utils;


import com.kloudnation.thinaer.model.BooleanOptionValue;
import com.kloudnation.thinaer.model.OptionType;

import junit.framework.TestCase;

import org.junit.Test;

public class BooleanOptionValueTest
        extends TestCase {

    @Test
    public void testBooleanOptionNull() {
        // arrange
        byte[] data = null;
        BooleanOptionValue optionValue = new BooleanOptionValue( OptionType.BOOLEAN, data );

        // act
        Boolean actual = optionValue.getValue();

        // assert
        assertNull( actual );
    }

    @Test
    public void testBooleanOptionEmpty() {
        // arrange
        byte[] data = new byte[]{};
        BooleanOptionValue optionValue = new BooleanOptionValue( OptionType.BOOLEAN, data );

        // act
        Boolean actual = optionValue.getValue();

        // assert
        assertNull( actual );
    }

    @Test
    public void testBooleanOptionTrue() {
        // arrange
        byte[] data = new byte[]{ 0x01 };
        BooleanOptionValue optionValue = new BooleanOptionValue( OptionType.BOOLEAN, data );

        // act
        Boolean actual = optionValue.getValue();

        // assert
        assertNotNull( actual );
        assertTrue( actual.booleanValue() );
    }

    @Test
    public void testBooleanOptionFalse() {
        // arrange
        byte[] data = new byte[]{ 0x00 };
        BooleanOptionValue optionValue = new BooleanOptionValue( OptionType.BOOLEAN, data );

        // act
        Boolean actual = optionValue.getValue();

        // assert
        assertNotNull( actual );
        assertFalse( actual.booleanValue() );    }
}
