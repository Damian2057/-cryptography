package crypto.project.Model.crypto.algorithm;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.crypto.component.DataBlock;
import crypto.project.Model.crypto.component.KeyBlock;
import crypto.project.Model.functional.PermutationFunction;
import crypto.project.Model.functional.XorFunction;
import crypto.project.Model.patterns.Tables;

public class Des {
    private byte[] leftText;
    private byte[] rightText;
    private byte[] key;
    private byte[] rightTextExtended;
    private byte[] tmp;
    private byte[] firstXor = new byte[48];
    private byte[] secondXor = new byte[32];
    private byte[] substitutionChoice = new byte[32];
    private byte[] permutation = new byte[32];
    private byte[] cipherText = new byte[64];
    private byte[] decipherText = new byte[64];

    private DataBlock dataBlock;
    private KeyBlock keyBlock;

    public byte[] encrypt(byte[] textBlock, byte[] keyBlc) {
        return null;
    }

    public byte[] decrypt(byte[] textBlock, byte[] keyBlc) {
        return null;
    }
}
