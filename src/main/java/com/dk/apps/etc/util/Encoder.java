package com.dk.apps.etc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Encoder {

    protected PrintStream pStream;

    public static void main(String[] args) throws Exception {
        System.out.println(new Encoder().encodePassword("admin"));
    }

    public String encodePassword(String password) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return encodeBuffer(messageDigest.digest(password.getBytes()));
    }

    public String encodeBuffer(byte abyte0[]) {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        try {
            encodeBuffer(((InputStream) (bytearrayinputstream)), ((OutputStream) (bytearrayoutputstream)));
        } catch (Exception exception) {
            throw new Error("ChracterEncoder::encodeBuffer internal error");
        }
        String s;
        try {
            s = bytearrayoutputstream.toString("UTF8");
        } catch (UnsupportedEncodingException unsupportedencodingexception) {
            throw new Error("CharacterEncoder::encodeBuffer internal error(2)");
        }
        return s;
    }

    void encodeBufferPrefix(OutputStream outputstream) throws IOException {
        pStream = new PrintStream(outputstream);
    }

    public void encodeBuffer(InputStream inputstream, OutputStream outputstream) throws IOException {
        byte abyte0[] = new byte[57];
        encodeBufferPrefix(outputstream);
        int j;
        do {
            j = readFully(inputstream, abyte0);
            if (j == -1)
                break;
            for (int i = 0; i < j; i += 3)
                if (i + 3 <= j)
                    encodeAtom(outputstream, abyte0, i, 3);
                else
                    encodeAtom(outputstream, abyte0, i, j - i);
        } while (j >= 57);
    }

    void encodeAtom(OutputStream outputstream, byte abyte0[], int i, int j) throws IOException {
        if (j == 1) {
            byte byte0 = abyte0[i];
            int k = 0;
            boolean flag = false;
            outputstream.write(pem_array[byte0 >>> 2 & 0x3f]);
            outputstream.write(pem_array[(byte0 << 4 & 0x30) + (k >>> 4 & 0xf)]);
            outputstream.write(61);
            outputstream.write(61);
        } else if (j == 2) {
            byte byte1 = abyte0[i];
            byte byte3 = abyte0[i + 1];
            int l = 0;
            outputstream.write(pem_array[byte1 >>> 2 & 0x3f]);
            outputstream.write(pem_array[(byte1 << 4 & 0x30) + (byte3 >>> 4 & 0xf)]);
            outputstream.write(pem_array[(byte3 << 2 & 0x3c) + (l >>> 6 & 3)]);
            outputstream.write(61);
        } else {
            byte byte2 = abyte0[i];
            byte byte4 = abyte0[i + 1];
            byte byte5 = abyte0[i + 2];
            outputstream.write(pem_array[byte2 >>> 2 & 0x3f]);
            outputstream.write(pem_array[(byte2 << 4 & 0x30) + (byte4 >>> 4 & 0xf)]);
            outputstream.write(pem_array[(byte4 << 2 & 0x3c) + (byte5 >>> 6 & 3)]);
            outputstream.write(pem_array[byte5 & 0x3f]);
        }
    }


    private static final char pem_array[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

    protected int readFully(InputStream inputstream, byte abyte0[]) throws IOException {
        for (int i = 0; i < abyte0.length; i++) {
            int j = inputstream.read();
            if (j == -1)
                return i;
            abyte0[i] = (byte) j;
        }

        return abyte0.length;
    }
}
