/*
 * Copyright 1999-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.apache.commons.io.input;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import junit.framework.TestCase;


/**
 * Test for the SwappedDataInputStream. This also 
 * effectively tests the underlying EndianUtils Stream methods.
 *
 * @author Henri Yandell (bayard at apache dot org)
 * @version $Revision: 1.7 $ $Date: 2004/02/23 05:02:25 $
 */

public class SwappedDataInputStreamTest extends TestCase {

    private SwappedDataInputStream sdis;

    public SwappedDataInputStreamTest(String name) {
        super(name);
    }

    public void setUp() {
        byte[] bytes = new byte[] {
            0x01,
            0x02,
            0x03,
            0x04,
            0x05,
            0x06,
            0x07,
            0x08
        };
        ByteArrayInputStream bais = new ByteArrayInputStream( bytes );
        this.sdis = new SwappedDataInputStream( bais );
    }

    public void tearDown() {
        this.sdis = null;
    }

    public void testReadBoolean() throws IOException {
        assertEquals( false, this.sdis.readBoolean() );
    }

    public void testReadByte() throws IOException {
        assertEquals( 0x01, this.sdis.readByte() );
    }

    public void testReadChar() throws IOException {
        assertEquals( (char) 0x0201, this.sdis.readChar() );
    }

    public void testReadDouble() throws IOException {
        assertEquals( Double.longBitsToDouble(0x0807060504030201L), this.sdis.readDouble(), 0 );
    }

    public void testReadFloat() throws IOException {
        assertEquals( Float.intBitsToFloat(0x04030201), this.sdis.readFloat(), 0 );
    }

    /*
    public void testReadFully() throws IOException {
    }
    */

    public void testReadInt() throws IOException {
        assertEquals( (int) 0x04030201, this.sdis.readInt() );
    }

    public void testReadLine() throws IOException {
        try {
            String unexpected = this.sdis.readLine();
            fail("readLine should be unsupported. ");
        } catch(UnsupportedOperationException uoe) {
        }
    }

    public void testReadLong() throws IOException {
        assertEquals( 0x0807060504030201L, this.sdis.readLong() );
    }

    public void testReadShort() throws IOException {
        assertEquals( (short) 0x0201, this.sdis.readShort() );
    }

    /*
    public void testReadUnsignedByte() throws IOException {
    }

    public void testReadUnsignedShort() throws IOException {
    }
    */

    public void testReadUTF() throws IOException {
        try {
            String unexpected = this.sdis.readUTF();
            fail("readUTF should be unsupported. ");
        } catch(UnsupportedOperationException uoe) {
        }
    }

    public void testSkipBytes() throws IOException {
        this.sdis.skipBytes(4);
        assertEquals( (int)0x08070605, this.sdis.readInt() );
    }

}
