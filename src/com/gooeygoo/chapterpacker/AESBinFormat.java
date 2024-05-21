package com.gooeygoo.chapterpacker;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;

import java.io.IOException;

/**
 * Modified version of code by DavidC, linked below
 * http://goofans.com/developers/game-file-formats/aes-encryption-format
 */
public class AESBinFormat
{
    private static final byte[] KEY = {0x0D, 0x06, 0x07, 0x07, 0x0C, 0x01, 0x08, 0x05,
            0x06, 0x09, 0x09, 0x04, 0x06, 0x0D, 0x03, 0x0F,
            0x03, 0x06, 0x0E, 0x01, 0x0E, 0x02, 0x07, 0x0B};

    private static final byte EOF_MARKER = (byte) 0xFD;


    public static byte[] decode(byte[] inputBytes) throws IOException
    {
        BufferedBlockCipher cipher = getCipher();

        byte[] outputBytes = new byte[cipher.getOutputSize(inputBytes.length)];

        int outputLen = cipher.processBytes(inputBytes, 0, inputBytes.length, outputBytes, 0);

        try {
            outputLen += cipher.doFinal(outputBytes, outputLen);
        }
        catch (InvalidCipherTextException e) {
            throw new IOException("Can't decrypt file: " + e.getLocalizedMessage());
        }

        for (int i = outputLen - 16; i < outputLen; ++i) {
            byte b = outputBytes[i];
            if (b == EOF_MARKER) {
                outputLen = i;
                break;
            }
        }

        byte[] finalBytes = new byte[outputLen];
        System.arraycopy(outputBytes, 0, finalBytes, 0, outputLen);
        return finalBytes;
    }

    private static BufferedBlockCipher getCipher()
    {
        BlockCipher engine = new AESEngine();
        BufferedBlockCipher cipher = new BufferedBlockCipher(new CBCBlockCipher(engine));

        cipher.init(false, new KeyParameter(KEY));
        return cipher;
    }
}