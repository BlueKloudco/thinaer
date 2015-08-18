package com.kloudnation.thinaer.utils;

import android.text.TextUtils;

import junit.framework.TestCase;

import org.junit.Test;

public class ByteUtilsTest
        extends TestCase {

    //region String Tests

    @Test
    public void testByteArrayToHexStringNoSeparator() {
        // arrange
        byte[] data = new byte[]{0x01, 0x02, 0x03, 0x04};
        String expected = "01020304";

        // act
        String actual = ByteUtils.byteArrayToHexString( data );

        // assert
        assertTrue( TextUtils.equals( expected, actual ) );
    }

    @Test
    public void testByteArrayToHexStringSeparator() {
        // arrange
        byte[] data = new byte[]{0x01, 0x02, 0x03, 0x04};
        String expected = "01,02,03,04";

        // act
        String actual = ByteUtils.byteArrayToHexString( data, "," );

        // assert
        assertTrue( TextUtils.equals( expected, actual ) );
    }

    @Test
    public void testHexStringToByteArrayNoSeparator() {
        // arrange
        String data = "01020304";
        byte[] expected = new byte[]{0x01, 0x02, 0x03, 0x04};

        // act
        byte[] actual = ByteUtils.hexStringToByteArray( data );

        // assert
        assertTrue( expected.length == actual.length );
        for ( int x = 0; x < expected.length; x++ ) {
            assertTrue( actual[x] == expected[x] );
        }
    }

    @Test
    public void testHexStringToByteArraySeparator() {
        // arrange
        String data = "01,02,03,04";
        byte[] expected = new byte[]{0x01, 0x02, 0x03, 0x04};

        // act
        byte[] actual = ByteUtils.hexStringToByteArray( data );

        // assert
        assertTrue( expected.length == actual.length );
        for ( int x = 0; x < expected.length; x++ ) {
            assertTrue( actual[x] == expected[x] );
        }
    }

    //endregion

    //region  Int Tests

    @Test
    public void testByteArrayToIntInvalidArgumentLow() {
        try {
            int actual = ByteUtils.byteArrayToInt( new byte[]{0x10, 0x10, 0x10} );
            fail( "Did not receive IllegalArgumentException" );
        } catch ( IllegalArgumentException iae ) {
            // pass
        } catch ( Exception e ) {
            fail( "Unexpected exception thrown" );
        }
    }

    @Test
    public void testByteArrayToIntLEInvalidArgumentHigh() {
        try {
            int actual = ByteUtils.byteArrayToInt( new byte[]{0x10, 0x10, 0x10, 0x10, 0x10} );
            fail( "Did not receive IllegalArgumentException" );
        } catch ( IllegalArgumentException iae ) {
            // pass
        } catch ( Exception e ) {
            fail( "Unexpected exception thrown" );
        }
    }

    @Test
    public void testByteArrayToIntLEInvalidArgumentLow() {
        try {
            int actual = ByteUtils.byteArrayToIntLE( new byte[]{0x10, 0x10, 0x10} );
            fail( "Did not receive IllegalArgumentException" );
        } catch ( IllegalArgumentException iae ) {
            // pass
        } catch ( Exception e ) {
            fail( "Unexpected exception thrown" );
        }
    }

    @Test
    public void testByteArrayToIntInvalidArgumentHigh() {
        try {
            int actual = ByteUtils.byteArrayToIntLE( new byte[]{0x10, 0x10, 0x10, 0x10, 0x10} );
            fail( "Did not receive IllegalArgumentException" );
        } catch ( IllegalArgumentException iae ) {
            // pass
        } catch ( Exception e ) {
            fail( "Unexpected exception thrown" );
        }
    }

    @Test
    public void testIntToByteArray() {
        // arrange
        int data = 496;
        byte[] expected = new byte[]{0x00, 0x00, 0x01, (byte) 0xF0};

        // act
        byte[] actual = ByteUtils.intToByteArray( data );

        //assert
        assertTrue( expected.length == actual.length );
        for ( int x = 0; x < expected.length; x++ ) {
            assertTrue( actual[x] == expected[x] );
        }
    }

    @Test
    public void testIntToByteArrayLE() {
        // arrange
        int data = 496;
        byte[] expected = new byte[]{(byte) 0xF0, 0x01, 0x00, 0x00};

        // act
        byte[] actual = ByteUtils.intToByteArrayLE( data );

        //assert
        assertTrue( expected.length == actual.length );
        for ( int x = 0; x < expected.length; x++ ) {
            assertTrue( actual[x] == expected[x] );
        }
    }

    @Test
    public void testByteArrayToInt() {
        // arrange
        byte[] data = new byte[]{0x00, 0x00, 0x01, (byte) 0xF0};
        int expected = 496;

        // act
        int actual = ByteUtils.byteArrayToInt( data );

        //assert
        assertTrue( expected == actual );
    }

    @Test
    public void testByteArrayToIntLE() {
        // arrange
        byte[] data = new byte[]{(byte) 0xF0, 0x01, 0x00, 0x00};
        int expected = 496;

        // act
        int actual = ByteUtils.byteArrayToIntLE( data );

        //assert
        assertTrue( expected == actual );
    }

    //endregion

    //region  Short Tests

    @Test
    public void testByteArrayToShortInvalidArgumentLow() {
        try {
            int actual = ByteUtils.byteArrayToShort( new byte[]{0x10} );
            fail( "Did not receive IllegalArgumentException" );
        } catch ( IllegalArgumentException iae ) {
            // pass
        } catch ( Exception e ) {
            fail( "Unexpected exception thrown" );
        }
    }

    @Test
    public void testByteArrayToShortInvalidArgumentHigh() {
        try {
            int actual = ByteUtils.byteArrayToShort( new byte[]{0x10, 0x10, 0x10} );
            fail( "Did not receive IllegalArgumentException" );
        } catch ( IllegalArgumentException iae ) {
            // pass
        } catch ( Exception e ) {
            fail( "Unexpected exception thrown" );
        }
    }

    @Test
    public void testByteArrayToShortLEInvalidArgumentLow() {
        try {
            int actual = ByteUtils.byteArrayToShortLE( new byte[]{0x10} );
            fail( "Did not receive IllegalArgumentException" );
        } catch ( IllegalArgumentException iae ) {
            // pass
        } catch ( Exception e ) {
            fail( "Unexpected exception thrown" );
        }
    }

    @Test
    public void testByteArrayToShortLEInvalidArgumentHigh() {
        try {
            int actual = ByteUtils.byteArrayToShortLE( new byte[]{0x10, 0x10, 0x10} );
            fail( "Did not receive IllegalArgumentException" );
        } catch ( IllegalArgumentException iae ) {
            // pass
        } catch ( Exception e ) {
            fail( "Unexpected exception thrown" );
        }
    }

    @Test
    public void testShortToByteArray() {
        // arrange
        short data = 496;
        byte[] expected = new byte[]{0x01, (byte) 0xF0};

        // act
        byte[] actual = ByteUtils.shortToByteArray( data );

        //assert
        assertTrue( expected.length == actual.length );
        for ( int x = 0; x < expected.length; x++ ) {
            assertTrue( actual[x] == expected[x] );
        }
    }

    @Test
    public void testShortToByteArrayLE() {
        // arrange
        short data = 496;
        byte[] expected = new byte[]{(byte) 0xF0, 0x01};

        // act
        byte[] actual = ByteUtils.shortToByteArrayLE( data );

        //assert
        assertTrue( expected.length == actual.length );
        for ( int x = 0; x < expected.length; x++ ) {
            assertTrue( actual[x] == expected[x] );
        }
    }

    @Test
    public void testByteArrayToShort() {
        // arrange
        byte[] data = new byte[]{0x01, (byte) 0xF0};
        short expected = 496;

        // act
        short actual = ByteUtils.byteArrayToShort( data );

        //assert
        assertTrue( expected == actual );
    }

    @Test
    public void testByteArrayToShortLE() {
        // arrange
        byte[] data = new byte[]{(byte) 0xF0, 0x01};
        short expected = 496;

        // act
        short actual = ByteUtils.byteArrayToShortLE( data );

        //assert
        assertTrue( expected == actual );
    }

    //endregion

    //region Extraction Tests

    @Test
    public void testSubRange() {
        // arrange
        byte[] data = new byte[]{0x02, 0x01, 0x06, 0x0E, (byte) 0xFF, (byte) 0xF0, 0x01, 0x02, 0x0A, (byte) 0xBA, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x05, (byte) 0xFF, (byte) 0xF0};
        byte[] expected = new byte[]{0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01};

        // act
        byte[] actual = ByteUtils.subRange( data, 10, 8 );

        //assert
        assertTrue( expected.length == actual.length );
        for ( int x = 0; x < expected.length; x++ ) {
            assertTrue( actual[x] == expected[x] );
        }
    }

    //endregion
}
