package crypto.project.Model.crypto.component;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.functional.PermutationFunction;
import crypto.project.Model.patterns.Tables;

public class KeyBlock {
    private int[] blockInt;

    private byte[] blockByte;
    private byte[] leftBlock;
    private byte[] rightBlock;
    private byte[] connectedBlock = new byte[56];
    private byte[] permutedChoiceTwo = new byte[48];

    private byte[] leftPattern = {
            57, 49, 41, 33, 25, 17,  9,
            1, 58, 50, 42, 34, 26, 18,
            10,  2, 59, 51, 43, 35, 27,
            19, 11,  3, 60, 52, 44, 36
    };
    private byte[] rightPattern = {
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14,  6, 61, 53, 45, 37, 29,
            21, 13,  5, 28, 20, 12,  4
    };

    public KeyBlock(byte[] block) {
        //blockInt = Converter.toIntegerTab(block);
        //blockByte = Converter.toByteTab(blockInt);
        leftBlock = PermutationFunction.permutation(leftPattern, Converter.toBinaryTab(block), 28);
        rightBlock = PermutationFunction.permutation(rightPattern, Converter.toBinaryTab(block), 28);
    }

    public void roundEncrypt(int round) {
        leftShift(Tables.shiftBits[round]);
        connectBlock();
        permutationChoiceTwo();
    }

    public void roundDecrypt(int round) {
        rightShift(Tables.shiftBits[round]);
        connectBlock();
        permutationChoiceTwo();
    }

    private void leftShift(byte times) {
        byte tmpL = 0;
        byte tmpR = 0;

        for (int j = 0; j < times; j++) {
            tmpL = leftBlock[0];
            tmpR = rightBlock[0];

            for (int i = 0; i < 27; i++) {
                leftBlock[i] = leftBlock[i + 1];
                rightBlock[i] = rightBlock[i + 1];
            }
            leftBlock[27] = tmpL;
            rightBlock[27] = tmpR;
        }
    }

    private void rightShift(byte times) {
        byte tmpL;
        byte tmpR;

        for (int j = 0; j < times; j++) {
            tmpL = leftBlock[27];
            tmpR = rightBlock[27];
            for (int i = 27; i > 0; i--) {
                leftBlock[i] = leftBlock[i - 1];
                rightBlock[i] = rightBlock[i - 1];
            }
            leftBlock[0] = tmpL;
            rightBlock[0] = tmpR;
        }
    }

    private void connectBlock() {
        System.arraycopy(leftBlock, 0, connectedBlock, 0, 28);
        System.arraycopy(rightBlock, 0, connectedBlock, 28, 28);
    }

    private void permutationChoiceTwo() {
        permutedChoiceTwo = PermutationFunction.permutation(Tables.PC2, connectedBlock, 48);
    }

    public byte[] getPermutedChoiceTwo() {
        return permutedChoiceTwo;
    }
}
