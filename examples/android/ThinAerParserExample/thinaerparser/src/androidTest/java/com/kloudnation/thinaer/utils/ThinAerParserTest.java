package com.kloudnation.thinaer.utils;

import com.kloudnation.thinaer.model.OptionType;

import junit.framework.TestCase;

import org.junit.Test;

public class ThinAerParserTest
        extends TestCase {


    @Override
    public void setUp() throws Exception {
        super.setUp();

        assertTrue( TestData.SCANRECORD_NOOPTIONS.length == 62 );
        assertTrue( TestData.SCANRECORD_OPTIONS.length == 62 );
    }

    @Test
    public void testAdvertisingPacket() {
        // arrange
        int expectedStart = 4;
        int expectedSize = 13;
        byte[] expectedPacket = new byte[]{(byte) 0xFF, (byte) 0xF0, 0x01, 0x12, 0x34, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01};

        // act
        ThinAerParser parser = new ThinAerParser( TestData.SCANRECORD_NOOPTIONS );

        // assert
        assertTrue( parser.advertisingPacketStart == expectedStart );
        assertTrue( parser.advertisingPacketSize == expectedSize );
        assertTrue( expectedPacket.length == parser.advertisingPacket.length );
        for ( int x = 0; x < expectedPacket.length; x++ ) {
            assertTrue( parser.advertisingPacket[x] == expectedPacket[x] );
        }
    }

    @Test
    public void testManufacturerSpecificData() {
        // arrange
        int expectedStart = 18;
        int expectedSize = 3;
        byte[] expectedPacket = new byte[]{(byte) 0xFF, (byte) 0xF0, 0x01};

        // act
        ThinAerParser parser = new ThinAerParser( TestData.SCANRECORD_NOOPTIONS );

        // assert
        assertTrue( parser.manufacturerSpecificDataStart == expectedStart );
        assertTrue( parser.manufacturerSpecificDataSize == expectedSize );
        assertTrue( expectedPacket.length == parser.manufacturerSpecificData.length );
        for ( int x = 0; x < expectedPacket.length; x++ ) {
            assertTrue( parser.manufacturerSpecificData[x] == expectedPacket[x] );
        }
    }

    @Test
    public void testOptions() {
        // arrange
        int expectedBattery = 100;
        int expectedTemperature = 27;
        boolean expectedBoolean = true;

        // act
        ThinAerParser parser = new ThinAerParser( TestData.SCANRECORD_OPTIONS );

        // assert
        assertNotNull( parser.options );

        assertTrue( parser.options.containsKey( OptionType.BATTERY ) );
        assertNotNull( parser.options.get( OptionType.BATTERY ) );
        assertEquals(
                parser.options.get( OptionType.BATTERY ).getValue(),
                expectedBattery
        );

        assertTrue( parser.options.containsKey( OptionType.TEMPERATURE ) );
        assertNotNull( parser.options.get( OptionType.TEMPERATURE ) );
        assertEquals(
                parser.options.get( OptionType.TEMPERATURE ).getValue(),
                expectedTemperature
        );

        assertTrue( parser.options.containsKey( OptionType.BOOLEAN ) );
        assertNotNull( parser.options.get( OptionType.BOOLEAN ) );
        assertEquals( expectedBoolean, parser.options.get( OptionType.BOOLEAN ).getValue() );
    }
}
