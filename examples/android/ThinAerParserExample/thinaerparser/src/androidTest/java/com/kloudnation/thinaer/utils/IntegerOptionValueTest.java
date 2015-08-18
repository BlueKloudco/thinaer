package com.kloudnation.thinaer.utils;

import com.kloudnation.thinaer.model.IntegerOptionValue;
import com.kloudnation.thinaer.model.OptionType;

import junit.framework.TestCase;

import org.junit.Test;

public class IntegerOptionValueTest
        extends TestCase {

    @Test
    public void testIntegerOptionNull() {
        // arrange
        byte[] data = null;
        IntegerOptionValue optionValue = new IntegerOptionValue( OptionType.BATTERY, data );

        // act
        Integer actual = optionValue.getValue();

        // assert
        assertNull( actual );
    }

    @Test
    public void testIntegerOptionOneByte() {
        // arrange
        byte[] data = new byte[] { 0x64 };
        IntegerOptionValue optionValue = new IntegerOptionValue( OptionType.BATTERY, data );
        Integer expected = 100;

        // act
        Integer actual = optionValue.getValue();

        // assert
        assertEquals( expected, actual );
    }

    @Test
    public void testIntegerOptionTwoBytes() {
        // arrange
        byte[] data = new byte[] { 0x00, 0x64 };
        IntegerOptionValue optionValue = new IntegerOptionValue( OptionType.BATTERY, data );
        Integer expected = 100;

        // act
        Integer actual = optionValue.getValue();

        // assert
        assertEquals( expected, actual );
    }

    @Test
    public void testIntegerOptionThreeBytes() {
        // arrange
        byte[] data = new byte[] { 0x00, 0x00, 0x64 };
        IntegerOptionValue optionValue = new IntegerOptionValue( OptionType.BATTERY, data );
        Integer expected = 100;

        // act
        Integer actual = optionValue.getValue();

        // assert
        assertEquals( expected, actual );
    }

    @Test
    public void testIntegerOptionFourBytes() {
        // arrange
        byte[] data = new byte[] { 0x00, 0x00, 0x00, 0x64 };
        IntegerOptionValue optionValue = new IntegerOptionValue( OptionType.BATTERY, data );
        Integer expected = 100;

        // act
        Integer actual = optionValue.getValue();

        // assert
        assertEquals( expected, actual );
    }

    @Test
    public void testIntegerOptionFiveBytes() {
        // arrange
        byte[] data = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x64 };
        IntegerOptionValue optionValue = new IntegerOptionValue( OptionType.BATTERY, data );

        // act
        Integer actual = optionValue.getValue();

        // assert
        assertNull( actual );
    }
}
