package crypto.project.Model.crypto.component;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.functional.PermutationFunction;
import crypto.project.Model.patterns.Tables;

public class DataBlock {

    private char[] block;
    private int[] blockInt;
    private byte[] blockByte;
    private byte[] blockInitialPermutation;
    private byte[] blockLeft = new byte[32];
    private byte[] blockRight = new byte[32];

    public DataBlock(char[] block) {
        this.block = block;
//        blockInt = Converter.toIntegerTab(block);
//        blockByte = Converter.toByteTab(blockInt);
        blockInitialPermutation = PermutationFunction.permutation(Tables.IP, blockByte, 64);
        divideBlock();
    }

    public DataBlock(byte[] block) {
        blockByte = block;
        blockInitialPermutation = PermutationFunction.permutation(Tables.IP, Converter.toBinaryTab(blockByte), 64);
        divideBlock();
    }

    private void divideBlock() {
        System.arraycopy(blockInitialPermutation, 0, blockLeft, 0, 32);
        System.arraycopy(blockInitialPermutation, 32, blockRight, 0, 32);
    }

    public byte[] getBlockLeft() {
        return blockLeft;
    }

    public byte[] getBlockRight() {
        return blockRight;
    }
}
