//
//------------------------------------------------------------
//      DESX Cryptographic Algorithm Implementation Project
//  Damian Biskupski
//  Mateusz Dangreaux
//                          2022
//------------------------------------------------------------
//
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
        //converting data block to 64 bits
        byte[] binaryText = Converter.byteTabToBinary(byteText);
        //converting key to 64 bits
        byte[] binaryKey = Converter.byteTabToBinary(byteKey);

        keyBlock = new KeyBlock(binaryKey);
        byte[][] keys = keyBlock.getFinal16SubKeys();
        dataBlock = new DataBlock(binaryText);

        //get left and right side
        byte[] left = dataBlock.getLeft();
        byte[] right = dataBlock.getRight();

        byte[] expandedRightSite = new byte[48];

        byte[] finalForm = new byte[64];

        for (int i = 0; i < 16; i++) {
            //Expanding permutation
            expandedRightSite = PermutationFunction.permutation(Tables.EP,right,48);
            //Get the i-subkey
            byte[] key = keys[i];
            expandedRightSite = XorFunction.xor(expandedRightSite,key);
            expandedRightSite = PermutationFunction.sBoxTransformTo32Bits(expandedRightSite);
            //Straight Permutation
            expandedRightSite = PermutationFunction.permutation(Tables.P, expandedRightSite,32);
            expandedRightSite = XorFunction.xor(left,expandedRightSite);
            left = right;
            right = expandedRightSite;
        }

        System.arraycopy(right, 0, finalForm, 0, 32);
        System.arraycopy(left, 0, finalForm, 32, 32);

        //Inverse Initial Permutation
        finalForm = PermutationFunction.permutation(Tables.IP1,finalForm,64);

        //Binary Array to Byte Array
        byte[] toByte = Converter.binaryChainToByteForm(finalForm);

        return toByte;
    }

    public byte[] decrypt(byte[] byteText, byte[] byteKey) {
        byte[] binaryText = Converter.byteTabToBinary(byteText);
        byte[] binaryKey = Converter.byteTabToBinary(byteKey);

        keyBlock = new KeyBlock(binaryKey);
        byte[][] keys = keyBlock.getFinal16SubKeys();
        dataBlock = new DataBlock(binaryText);

        byte[] left = dataBlock.getLeft();
        byte[] right = dataBlock.getRight();

        byte[] expandedRightSite = new byte[48];

        byte[] finalForm = new byte[64];

        for (int i = 15; i >= 0; i--) {
            expandedRightSite = PermutationFunction.permutation(Tables.EP,right,48);
            byte[] key = keys[i];

            expandedRightSite = XorFunction.xor(expandedRightSite,key);
            expandedRightSite = PermutationFunction.sBoxTransformTo32Bits(expandedRightSite);
            expandedRightSite = PermutationFunction.permutation(Tables.P, expandedRightSite,32);
            expandedRightSite = XorFunction.xor(left,expandedRightSite);

            left = right;
            right = expandedRightSite;
        }

        System.arraycopy(right, 0, finalForm, 0, 32);
        System.arraycopy(left, 0, finalForm, 32, 32);

        finalForm = PermutationFunction.permutation(Tables.IP1,finalForm,64);

        byte[] toByte = Converter.binaryChainToByteForm(finalForm);

        return toByte;
    }
}
