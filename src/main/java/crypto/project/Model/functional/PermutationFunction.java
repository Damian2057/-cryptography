package crypto.project.Model.functional;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.patterns.Tables;

public class PermutationFunction {
    public static byte[] permutation(byte[] pattern, byte[] block, int length) {
        byte[] blockDestination = new byte[length];
        for (int i = 0; i < length; i++) {
            blockDestination[i] = block[pattern[i]-1];
        }
        return blockDestination;
    }

    public static byte[] calculate32BitsSBox(byte[] binaryBlock) {
        byte[] finalResult = new byte[32];
        for (int i = 0; i < 8; i++) {
            byte[] split6bits = get6bits(binaryBlock,i);
            int row = getRow(split6bits);
            int col = getCol(split6bits);
            byte number = Tables.SBOX[i][row][col];
            byte[] binaryForm = Converter.byteNumberToBinaryChain(number);
            System.arraycopy(binaryForm,0,finalResult,i*4,4);

        }
        return finalResult;
    }

    public static byte[] get6bits(byte[] chain, int round) {
        byte[] bit6 = new byte[6];
        System.arraycopy(chain,round*6, bit6,0,6);
        return bit6;
    }

    public static int getRow(byte[] chain) {
        return chain[0]*2+ chain[5];
    }

    public static int getCol(byte[] chain) {
        return chain[1]*8+chain[2]*4+chain[3]*2+chain[4];
    }
}
