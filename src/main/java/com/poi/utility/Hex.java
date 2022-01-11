package com.poi.utility;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

/**
 * @author prayugo
 */
public final class Hex {

	public static final String Encoding = "UTF-8";
	public static final int PadLeft = 0;
	public static final int PadRight = 1;

	/* ---------------------------------------------------------------------- */
	/* Convert hexadecimal into byte array.								   */
	/* ---------------------------------------------------------------------- */
	public static byte[] hex2Byte( String h ) {
		if( ( h.length() % 2 ) != 0 ) {
			h = "0" + h;
		}

		int l	= h.length() / 2;
		byte[] r = new byte[ l ];

		for( int i = 0, j = 0, k = h.length(); i < k; i += 2, j++ ) {
			r[ j ] = Short.valueOf( h.substring( i, i+2 ),16 ).byteValue();
		}

		return r;
	}

	/* ---------------------------------------------------------------------- */
	/* Convert hexadecimal into ascii string								  */
	/* ---------------------------------------------------------------------- */
	public static String hex2String( String h ) {
		return ( new String( hex2Byte( h ) ) );
	}

	/* ---------------------------------------------------------------------- */
	/* Convert byte array into hexadecimal string.							*/
	/* ---------------------------------------------------------------------- */
	public static String byte2Hex( byte[] b ) {
		StringBuilder sbuf = new StringBuilder();

		for( int i = 0, n = b.length; i < n; i++ ) {

			byte hiByte = ( byte ) ( ( b[ i ] & 0xF0 ) >> 4 );
			byte loByte = ( byte )   ( b[ i ] & 0x0F );

			sbuf.append( Character.forDigit( hiByte, 16 ) );
			sbuf.append( Character.forDigit( loByte, 16 ) );
		}

		return sbuf.toString();
	}

	/* ---------------------------------------------------------------------- */
	/* Convert ascii string into hexadecimal string.						  */
	/* ---------------------------------------------------------------------- */
	public static String String2Hex( String s ) {
		return ( byte2Hex( s.getBytes(  ) ) );
	}

	/* ---------------------------------------------------------------------- */
	/* Convert ascii int into binary string representation					*/
	/* ---------------------------------------------------------------------- */
	public static byte[] str2bcd( int argInt ) {
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		o.write( argInt >> 8 );
		o.write( argInt );

		return o.toByteArray();
	}

	/* ---------------------------------------------------------------------- */
	/* Convert binary int into ascii int representation					   */
	/* ---------------------------------------------------------------------- */
	public static int bcd2str( String argInt ) {
		try {
			byte[] b = argInt.getBytes( Encoding );

			return ( ( ( b[ 0 ] ) & 0xFF ) << 8 ) | ( ( b[ 1 ] ) & 0xFF );
		}
		catch( UnsupportedEncodingException e ) {
			e.printStackTrace();
		}

		return 0;
	}

	/* ---------------------------------------------------------------------- */
	/* Convert binary int into ascii int representation					   */
	/* ---------------------------------------------------------------------- */
	public static int bcd2str( byte[] argInt ) {
		try {
			return ( ( ( argInt[ 0 ] ) & 0xFF ) << 8 ) | ( ( argInt[ 1 ] ) & 0xFF );
		}
		catch( Exception e ) {
			e.printStackTrace();
		}

		return 0;
	}

	public static String hexString( byte[] b ) {
		return hexString( b, 0, b.length );
	}

	public static String hexString( byte[] b, int offset, int len ) {
		StringBuilder d = new StringBuilder( b.length * 2 );

		for( int i = 0; i < len; i++ ) {
			char hi = Character.forDigit( b[(offset + i)] >> 4 & 0xF, 16 );
			char lo = Character.forDigit( b[(offset + i)] & 0xF, 16 );

			d.append( Character.toUpperCase(hi) );
			d.append( Character.toUpperCase(lo) );
		}

		return d.toString();
	}

	/**
	 * xor two hexadecimal string
	 * @param a first hexadecimal
	 * @param b second hexadecimal
	 * @return xor result
	 */
	public static String xorHex( String a, String b ) {
		char[] chars = new char[ a.length() ];

		for( int i = 0; i < chars.length; i++ ) {
			chars[i] = Hex.toHex( Hex.fromHex( a.charAt(i) ) ^ Hex.fromHex( b.charAt(i) ) );
		}

		return new String( chars );
	}

	private static int fromHex( char c ) {
		if( c >= '0' && c <= '9' )
			return c - '0';

		if( c >= 'A' && c <= 'F' )
			return c - 'A' + 10;

		if( c >= 'a' && c <= 'f' )
			return c - 'a' + 10;

		throw new IllegalArgumentException( "not.a.hexadecimal("+ c +")" );
	}

	private static char toHex( int nibble ) {
		if( nibble < 0 || nibble > 15 ) {
			throw new IllegalArgumentException( "not.a.hexadecimal("+ nibble +")" );
		}

		return "0123456789ABCDEF".charAt( nibble );
	}

	public static int byteArrayToInt( byte[] b ) {
		final ByteBuffer bb = ByteBuffer.wrap( b );
		bb.order( ByteOrder.BIG_ENDIAN );

		return bb.getInt();
	}

	public static byte[] intToByteArray( int i ) {
		final ByteBuffer bb = ByteBuffer.allocate( Integer.SIZE / Byte.SIZE );
		bb.order( ByteOrder.BIG_ENDIAN );
		bb.putInt( i );

		return bb.array();
	}

	public static int byteArrToInt( byte[] b ) { 
		return ( b[1] & 0xFF ) | ( b[0] & 0xFF ) << 8;
	}

	public static byte[] intToByteArr( int a ) {
		return new byte[] { ( byte ) ( ( a >> 8 ) & 0xFF ),   
							( byte ) ( a & 0xFF ) };
	}

	public static byte[] concat( byte[] a, byte[] b ) {
		ByteBuffer bb = ByteBuffer.allocate( a.length + b.length );
		bb.put( a );
		bb.put( b );

		return bb.array();
	}

	public static boolean ishex( String str ) {
		return str.matches( "^[0-9a-fA-F]+$" );
	}

	public static byte[] byteBuffer2byte(  ByteBuffer byteBuffer ) {
		byte[] bytesArray = new byte[ byteBuffer.remaining() ];
		byteBuffer.get( bytesArray, 0, bytesArray.length );
		return bytesArray;
	}

	public static String randomHex( int num_char ) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder();

		while( sb.length() < num_char )
			sb.append( Integer.toHexString( r.nextInt() ) );

		return sb.toString().substring( 0,num_char ).toUpperCase();
	}

	public static String encode64( byte[] data ) {
		char[] tbl = {
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
			'Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f',
			'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
			'w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/' };

		StringBuilder buffer = new StringBuilder();
		int pad = 0;
		for( int i = 0; i < data.length; i += 3 ) {
			int b = ( ( data[i] & 0xFF ) << 16 ) & 0xFFFFFF;
			if( i + 1 < data.length ) {
				b |= (data[i+1] & 0xFF) << 8;
			}
			else
				pad++;

			if( i + 2 < data.length ) {
				b |= ( data[i+2] & 0xFF );
			}
			else
				pad++;

			for( int j = 0; j < 4 - pad; j++ ) {
				int c = ( b & 0xFC0000 ) >> 18;
				buffer.append( tbl[c] );
				b <<= 6;
			}
		}
		for( int j = 0; j < pad; j++ ) {
			buffer.append( "=" );
		}
		return buffer.toString();
	}

	public static byte[] decode64( String data ) {
		int[] tbl = {
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
			55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2,
			3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
			20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30,
			31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
			48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

		byte[] bytes = data.getBytes();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		for( int i = 0; i < bytes.length; ) {
			int b = 0;
			if( tbl[ bytes[i] ] != -1 ) {
				b = ( tbl[ bytes[i] ] & 0xFF ) << 18;
			}
			else {  // skip unknown characters
				i++;
				continue;
			}

			int num = 0;
			if( i + 1 < bytes.length && tbl[ bytes[i+1] ] != -1 ) {
				b = b | (( tbl[ bytes[i+1] ] & 0xFF ) << 12 );
				num++;
			}
			if( i + 2 < bytes.length && tbl[ bytes[i+2] ] != -1 ) {
				b = b | (( tbl[ bytes[i+2] ] & 0xFF ) << 6 );
				num++;
			}
			if( i + 3 < bytes.length && tbl[ bytes[i+3] ] != -1 ) {
				b = b | ( tbl[ bytes[i+3] ] & 0xFF );
				num++;
			}

			while( num > 0 ) {
				int c = (b & 0xFF0000) >> 16;
				buffer.write((char)c);
				b <<= 8;
				num--;
			}
			i += 4;
		}
		return buffer.toByteArray();
	}

	public static String hexDump( byte[] array, int offset, int length ) {
		final int width = 16;
		StringBuilder builder = new StringBuilder();

		for( int rowOffset = offset; rowOffset < offset + length; rowOffset += width ) {
			builder.append(String.format( "%06d:  ", rowOffset));

			for( int index = 0; index < width; index++ ) {
				if( rowOffset + index < array.length ) {
					builder.append( String.format( "%02x ", array[rowOffset + index] ) );
				}
				else
					builder.append( "   " );
			}

			if( rowOffset < array.length ) {
				int asciiWidth = Math.min(width, array.length - rowOffset);
				builder.append("  |  ");
				try {
					builder.append( new String( array, rowOffset, asciiWidth, "UTF-8" ).replaceAll( "\r\n", " " ).replaceAll( "\n", " " ) );
				}
				catch( Exception ignored ) {
					// If UTF-8 isn't available as an encoding then what can we do?!
				}
			}
			builder.append(String.format("%n"));
		}

		return builder.toString();
	}

}
