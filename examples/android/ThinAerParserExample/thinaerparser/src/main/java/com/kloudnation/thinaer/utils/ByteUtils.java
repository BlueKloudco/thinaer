package com.kloudnation.thinaer.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Utility class to handle extracting byte[] arrays and converting byte[] values to other data types
 */
public class ByteUtils {
    private static final String TAG = ByteUtils.class.getSimpleName();

    private ByteUtils() {
    }

    //region String Conversions

    private static final String HEXES = "0123456789ABCDEF";

    public static String byteArrayToHexString( final byte[] array, String separator ) {
        final StringBuffer sb = new StringBuffer();
        boolean firstEntry = true;
        for ( final byte b : array ) {
            if ( !firstEntry ) {
                sb.append( separator );
            }
            sb.append( HEXES.charAt( (b & 0xF0) >> 4 ) );
            sb.append( HEXES.charAt( (b & 0x0F) ) );
            firstEntry = false;
        }
        return sb.toString();
    }

    public static String byteArrayToHexString( final byte[] array ) {
        return byteArrayToHexString( array, "" );
    }

    public static byte[] hexStringToByteArray( final String byteString ) {
        String value = byteString.replaceAll( "[^A-Za-z0-9]", "" );
        int len = value.length();
        byte[] data = new byte[len / 2];
        for ( int i = 0; i < len; i += 2 ) {
            data[i / 2] = (byte) (
                    (Character.digit( value.charAt( i ), 16 ) << 4)
                            + Character.digit( value.charAt( i + 1 ), 16 )
            );
        }
        return data;
    }

    //endregion

    //region Int Conversions

    public static int byteArrayToInt( byte[] bytes ) throws IllegalArgumentException {
        if ( bytes.length != (Integer.SIZE / Byte.SIZE) ) {
            throw new IllegalArgumentException( "Incorrect array size to convert to an int" );
        }
        ByteBuffer buffer = ByteBuffer.wrap( bytes );
        buffer.order( ByteOrder.BIG_ENDIAN );
        return buffer.getInt();
    }

    public static byte[] intToByteArray( int value ) {
        ByteBuffer buffer = ByteBuffer.allocate( Integer.SIZE / Byte.SIZE );
        buffer.order( ByteOrder.BIG_ENDIAN );
        buffer.putInt( value );
        return buffer.array();
    }

    public static int byteArrayToIntLE( byte[] bytes ) throws IllegalArgumentException {
        if ( bytes.length != (Integer.SIZE / Byte.SIZE) ) {
            throw new IllegalArgumentException( "Incorrect array size to convert to an int" );
        }
        ByteBuffer buffer = ByteBuffer.wrap( bytes );
        buffer.order( ByteOrder.LITTLE_ENDIAN );
        return buffer.getInt();
    }

    public static byte[] intToByteArrayLE( int value ) {
        ByteBuffer buffer = ByteBuffer.allocate( Integer.SIZE / Byte.SIZE );
        buffer.order( ByteOrder.LITTLE_ENDIAN );
        buffer.putInt( value );
        return buffer.array();
    }

    public static int intFromSingleByte( byte b ) {
        return b & 0xFF;
    }

    //endregion

    //region Short Conversions

    public static Short byteArrayToShort( byte[] bytes ) {
        if ( bytes.length != (Short.SIZE / Byte.SIZE) ) {
            throw new IllegalArgumentException( "Incorrect array size to convert to a short" );
        }
        ByteBuffer buffer = ByteBuffer.wrap( bytes );
        buffer.order( ByteOrder.BIG_ENDIAN );
        return buffer.getShort();
    }

    public static byte[] shortToByteArray( short value ) {
        ByteBuffer buffer = ByteBuffer.allocate( Short.SIZE / Byte.SIZE );
        buffer.order( ByteOrder.BIG_ENDIAN );
        buffer.putShort( value );
        return buffer.array();
    }

    public static Short byteArrayToShortLE( byte[] bytes ) {
        if ( bytes.length != (Short.SIZE / Byte.SIZE) ) {
            throw new IllegalArgumentException( "Incorrect array size to convert to a short" );
        }
        ByteBuffer buffer = ByteBuffer.wrap( bytes );
        buffer.order( ByteOrder.LITTLE_ENDIAN );
        return buffer.getShort();
    }

    public static byte[] shortToByteArrayLE( short value ) {
        ByteBuffer buffer = ByteBuffer.allocate( Short.SIZE / Byte.SIZE );
        buffer.order( ByteOrder.LITTLE_ENDIAN );
        buffer.putShort( value );
        return buffer.array();
    }

    //endregion

    //region Byte Extraction

    public static byte[] subRange( final byte[] bytes, int start, int length ) {
        return Arrays.copyOfRange( bytes, start, start + length );
    }

    //endregion
}
