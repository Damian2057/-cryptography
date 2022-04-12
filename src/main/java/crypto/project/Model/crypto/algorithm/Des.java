package crypto.project.Model.crypto.algorithm;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.crypto.component.DataBlock;
import crypto.project.Model.crypto.component.KeyBlock;
import crypto.project.Model.functional.PermutationFunction;
import crypto.project.Model.functional.XorFunction;
import crypto.project.Model.patterns.Tables;

public class Des {

    private DataBlock dataBlock;
    private KeyBlock keyBlock;

    public byte[] encrypt(byte[] byteText, byte[] byteKey) {
        byte[] binaryText = Converter.byteTabToBinary(byteText);
        byte[] binaryKey = Converter.byteTabToBinary(byteKey);

        keyBlock = new KeyBlock(binaryKey);
        byte[][] keys = keyBlock.getFinal16SubKeys();
        dataBlock = new DataBlock(binaryText);

        byte[] left = dataBlock.getLeft();
        byte[] right = dataBlock.getRight();

        byte[] expandedRight = new byte[48];

        byte[] finalForm = new byte[64];

        for (int i = 0; i < 16; i++) {
            expandedRight = PermutationFunction.permutation(Tables.EP,right,48);
            byte[] key = keys[i];
            expandedRight = XorFunction.xor(expandedRight,key);
            expandedRight = PermutationFunction.calculate32BitsSBox(expandedRight);
            expandedRight = PermutationFunction.permutation(Tables.P, expandedRight,32);
            expandedRight = XorFunction.xor(left,expandedRight);
            left = right;
            right = expandedRight;
        }

        System.arraycopy(right, 0, finalForm, 0, 32);
        System.arraycopy(left, 0, finalForm, 32, 32);

        finalForm = PermutationFunction.permutation(Tables.IP1,finalForm,64);


        byte[] toByte = Converter.binaryChainToByteForm(finalForm);

        return toByte;
    }

    public byte[] decrypt(byte[] byteText, byte[] byteKey) {

        byte[] binaryText = Converter.byteTabToBinary(byteText);
       // byte[] binaryText = Converter.toBinaryTab(byteText); //HERE PROBLEM?
        byte[] binaryKey = Converter.byteTabToBinary(byteKey);

        keyBlock = new KeyBlock(binaryKey);
        byte[][] keys = keyBlock.getFinal16SubKeys();
        dataBlock = new DataBlock(binaryText);

        byte[] left = dataBlock.getLeft();
        byte[] right = dataBlock.getRight();

        byte[] expandedRight = new byte[48];

        byte[] finalForm = new byte[64];

        for (int i = 15; i >= 0; i--) {
            expandedRight = PermutationFunction.permutation(Tables.EP,right,48);
            byte[] key = keys[i];
            expandedRight = XorFunction.xor(expandedRight,key);
            expandedRight = PermutationFunction.calculate32BitsSBox(expandedRight);
            expandedRight = PermutationFunction.permutation(Tables.P, expandedRight,32);
            expandedRight = XorFunction.xor(left,expandedRight);
            left = right;
            right = expandedRight;
        }

        System.arraycopy(right, 0, finalForm, 0, 32);
        System.arraycopy(left, 0, finalForm, 32, 32);

        finalForm = PermutationFunction.permutation(Tables.IP1,finalForm,64);


        byte[] toByte = Converter.binaryChainToByteForm(finalForm);

        return toByte;
    }


}
