package crypto.project.Model.crypto.component;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.functional.PermutationFunction;

public class KeyBlock {

    private final byte[] desKey;
    private byte[] leftKey;
    private byte[] rightKey;
    private byte[] PC2 = new byte[48];
    private byte[] connected = new byte[56];
    private final byte[] leftKeyPattern = {
            57, 49, 41, 33, 25, 17,  9,
            1, 58, 50, 42, 34, 26, 18,
            10,  2, 59, 51, 43, 35, 27,
            19, 11,  3, 60, 52, 44, 36
    };
    private final byte[] rightKeyPattern = {
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14,  6, 61, 53, 45, 37, 29,
            21, 13,  5, 28, 20, 12,  4
    };
    private final byte[] PC2Pattern = {
            14, 17, 11, 24,  1,  5,  3,
            28, 15,  6, 21, 10, 23, 19,
            12,  4, 26,  8, 16,  7, 27,
            20, 13,  2, 41, 52, 31, 37,
            47, 55, 30, 40, 51, 45, 33,
            48, 44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };
    private final byte[] shiftTablePattern = {
            1, 1, 2, 2, 2, 2, 2, 2,
            1, 2, 2, 2, 2, 2, 2, 1, 0
    };

    public KeyBlock(byte[] desKey) {
        this.desKey = desKey;
        leftKey = PermutationFunction.permute(leftKeyPattern, Converter.toBinaryTab(desKey),28);
        rightKey = PermutationFunction.permute(rightKeyPattern, Converter.toBinaryTab(desKey),28);
    }

    public void encryptByRound(int round) {
        left(shiftTablePattern[round]);
        connectBlocks();
        permPC2();
    }

    public void decryptByRound(int round) {
        right(shiftTablePattern[round]);
        connectBlocks();
        permPC2();
    }

    private void left(byte x) {
        byte leftTemp = 0;
        byte rightTemp = 0;
        for (int i = 0; i < x; i++) {
            leftTemp = leftKey[0];
            rightTemp = rightKey[0];

            for (int j = 0; j < 27; j++) {
                leftKey[j] = leftKey[j+1];
                rightKey[j] = rightKey[j+1];
            }
            leftKey[27] = leftTemp;
            rightKey[27] = rightTemp;
        }
    }

    private void right(byte x) {
        byte leftTemp = 0;
        byte rightTemp = 0;
        for (int i = 0; i < x; i++) {
            leftTemp = leftKey[27];
            rightTemp = rightKey[27];

            for (int j = 27; j > 0; j--) {
                leftKey[j] = leftKey[j-1];
                rightKey[j] = rightKey[j-1];
            }
            leftKey[0] = leftTemp;
            rightKey[0] = rightTemp;
        }
    }

    public byte[] getPC2() {
        return PC2;
    }

    private void connectBlocks() {
        System.arraycopy(leftKey,0, connected, 0,28);
        System.arraycopy(rightKey, 0, connected, 28, 28);
    }

    private void permPC2() {
        PC2 = PermutationFunction.permute(PC2Pattern, connected,48);
    }
}
