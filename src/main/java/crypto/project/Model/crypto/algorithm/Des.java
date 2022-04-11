package crypto.project.Model.crypto.algorithm;

import crypto.project.Model.crypto.component.DataBlock;
import crypto.project.Model.crypto.component.KeyBlock;

public class Des { ///ERROR IN DES
    private DataBlock dataBlock;
    private KeyBlock keyBlock;

    public byte[] encrypt(byte[] binaryText, byte[] binaryKey) {
        keyBlock = new KeyBlock(binaryKey);
        byte[][] keys = keyBlock.getFinal16SubKeys();
        return null;
    }


    public byte[] decrypt(byte[] binaryText, byte[] binaryKey) {
        return null;
    }


}
