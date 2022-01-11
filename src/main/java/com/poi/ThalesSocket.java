package com.poi;

import com.poi.utility.Hex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

class ThalesSocket {

    private Socket socket;

    private DataInputStream dis  = null;
    private DataOutputStream dos = null;
    /*
    public boolean connect( MSocket m ) {
        try {
            socket = new Socket( m.getHost(), m.getPort() );

            dis = new DataInputStream( socket.getInputStream() );
            dos = new DataOutputStream( socket.getOutputStream() );

            return true;
        }
        catch( Exception e ) {
            log.error( "unable connet to hsm device at( " + m.getHost() + ":" + m.getPort() + " )", e );
        }

        return false;
    }

    public String sendReceive( String data ) throws Exception {
        byte[] r = null;
        try {
            String c = data.replaceAll( "\\s+", "" );
            String[] w = c.split( "\\|" );

            if( debug )
                log.info( "hsm.request = " + c );

            byte[] bdata = new byte[0];

            for( String i : w ) {
                if( i.startsWith( "#" ) ) {
                    bdata = Hex.concat( bdata, Hex.hex2Byte( i.substring(1) ) );
                }
                else {
                    bdata = Hex.concat( bdata, i.getBytes( "UTF-8" ) );
                }
            }

            // -- msg.len(2):header(4):cmd(n)
            byte[] M = new byte[ bdata.length+6 ];
            M[0] = 0;
            M[1] = 0;
            M[2] = 0;
            M[3] = 0;
            M[4] = 0;
            M[5] = 0;

            int l = bdata.length+4;
            String lh = Integer.toHexString( l );
            byte[] k = Hex.hex2Byte( lh );

            if( k.length > 1 ) {
                M[0] = k[0];
                M[1] = k[1];
            }
            else {
                M[1] = k[0];
            }

            // -- src, pos, dst, pos, len
            System.arraycopy( bdata, 0, M, 6, bdata.length );
            dos.write( M );
            dos.flush();

            r = receive();

            if( debug )
                log.info( "hsm.response = " + Hex.byte2Hex( r ).toUpperCase() );
        }
        catch( Exception e ) {
            log.error( "error.in.sendreceive", e );
        }
        finally {
            shutdown();
        }

        return Hex.byte2Hex( r ).toUpperCase();
    } */

    private byte[] receive() throws Exception {
        byte[] l = new byte[2];
        this.dis.readFully( l );

        int ln = Integer.parseInt( Hex.byte2Hex( l ), 16 );
        byte[] data = new byte[ln];

        dis.readFully( data );
        byte[] r = new byte[data.length-4];
        // -- src, pos, dst, pos, len
        System.arraycopy( data, 4, r, 0, data.length-4 );

        return r;
    }

    public void shutdown() {
        try {
            socket.shutdownInput();
            socket.close();
        }
        catch( Exception e ) { }

        try {
            dis.close();
        }
        catch( Exception e ) { }

        try {
            dos.close();
        }
        catch( Exception e ) { }
        socket = null; dis = null; dos = null;
    }
}
