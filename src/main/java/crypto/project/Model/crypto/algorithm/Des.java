package crypto.project.Model.crypto.algorithm;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.crypto.component.DataBlock;
import crypto.project.Model.crypto.component.KeyBlock;
import crypto.project.Model.functional.PermutationFunction;
import crypto.project.Model.functional.XorFunction;
import crypto.project.Model.patterns.Tables;

import java.util.Arrays;

public class Des {
    private DataBlock dataBlock;
    private KeyBlock keyBlock;

    public byte[] encrypt(byte[] textBlock, byte[] keyBlock) {
        byte[] binaryTextForm = Converter.toBinaryTab(textBlock);
        byte[] binaryKeyForm = Converter.toBinaryTab(keyBlock);
        this.dataBlock = new DataBlock(binaryTextForm);
        this.keyBlock = new KeyBlock(binaryKeyForm);


        return null;
    }

    public byte[] decrypt(byte[] textBlock, byte[] keyBlc) {
        return null;
    }
}
