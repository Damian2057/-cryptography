package crypto.project.Model.crypto.algorithm;

import crypto.project.Model.crypto.component.DataBlock;
import crypto.project.Model.crypto.component.KeyBlock;

public class Des {

    private KeyBlock keyBlock;
    private DataBlock dataBlock;


    private byte[] leftSideOfText;
    private byte[] rightSideOfText;

    // Const
    // Initial Permutation Table


    public byte[] codeText(byte[] text, byte[] desKey) {
        keyBlock = new KeyBlock(desKey);
        dataBlock = new DataBlock(text); // 8 bajtow > nie 64 bity przekazywane

        return null;
    }

    public byte[] decodeText(byte[] text, byte[] desKey) {
        return null;
    }
}
