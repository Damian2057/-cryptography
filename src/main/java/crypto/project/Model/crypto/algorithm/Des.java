package crypto.project.Model.crypto.algorithm;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.crypto.component.DataBlock;
import crypto.project.Model.crypto.component.KeyBlock;
import crypto.project.Model.functional.PermutationFunction;
import crypto.project.Model.functional.XorFunction;
import crypto.project.Model.patterns.Tables;

public class Des {

    private KeyBlock keyBlock;
    private DataBlock dataBlock;

    private byte[] leftSideOfText;
    private byte[] rightSideOfText;

    private byte[] permutedKey;
    private byte[] extendedRightSideOfText;
    private byte[] afterFirstXOR = new byte[48];
    private byte[] afterSecondXOR = new byte[32];
    private byte[] substitutionChoice = new byte[32];
    private byte[] permutation = new byte[32];
    private byte[] tmp;

    private byte[] finalText = new byte[64];

    // Initial Permutation Table
    private final byte[] initialPattern = {
            16,  7, 20, 21, 29, 12, 28, 17,
            1, 15, 23, 26,  5, 18, 31, 10,
            2,  8, 24, 14, 32, 27,  3,  9,
            19, 13, 30,  6, 22, 11,  4, 25
    };
    private final byte[] expansionPattern = {
            32,  1,  2,  3,  4,  5,
            4,  5,  6,  7,  8,  9,
            8,  9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32,  1
    };
    private final byte[] finalPermutationPattern = {
            40,  8, 48, 16, 56, 24, 64, 32,
            39,  7, 47, 15, 55, 23, 63, 31,
            38,  6, 46, 14, 54, 22, 62, 30,
            37,  5, 45, 13, 53, 21, 61, 29,
            36,  4, 44, 12, 52, 20, 60, 28,
            35,  3, 43, 11, 51, 19, 59, 27,
            34,  2, 42, 10, 50, 18, 58, 26,
            33,  1, 41,  9, 49, 17, 57, 25
    };

    public byte[] codeText(byte[] text, byte[] desKey) {
        keyBlock = new KeyBlock(desKey);
        dataBlock = new DataBlock(text);
        leftSideOfText = dataBlock.getTextLeft();
        rightSideOfText = dataBlock.getTextRight();

        byte[] box;
        byte value;

        for (int i = 0; i < 16; i++) {
            this.tmp = this.rightSideOfText;
            rightExpansionInit();
            keyBlock.encryptByRound(i);
            this.permutedKey = keyBlock.getKeys();

            this.afterFirstXOR = XorFunction.xorBytes(extendedRightSideOfText, permutedKey);

            for (int j = 0; j < 8; j++) {
                box = Converter.getCountOfBytes(afterFirstXOR,j*6, 6);
                value = Tables.SBOX[j][getValue(box)];
                System.arraycopy(Converter.byteToBinary(value),0, substitutionChoice,j*4,4);
            }

            permutation = PermutationFunction.permute(initialPattern, substitutionChoice,32);

            afterSecondXOR = XorFunction.xorBytes(leftSideOfText, permutation);

            leftSideOfText = tmp;
            rightSideOfText = afterSecondXOR;
        }

        System.arraycopy(rightSideOfText,0,finalText,0,32);
        System.arraycopy(leftSideOfText,0,finalText,32,32);

        finalText = PermutationFunction.permute(finalPermutationPattern, finalText,64);

        return finalText;
    }

    public byte[] decodeText(byte[] text, byte[] desKey) {
        keyBlock = new KeyBlock(desKey);
        dataBlock = new DataBlock(text);
        leftSideOfText = dataBlock.getTextLeft();
        rightSideOfText = dataBlock.getTextRight();

        byte[] box;
        byte value;

        for (int i = 16; i > 0; i--) {
            this.tmp = this.rightSideOfText;
            rightExpansionInit();
            keyBlock.decryptByRound(i);
            this.permutedKey = keyBlock.getKeys();

            this.afterFirstXOR = XorFunction.xorBytes(extendedRightSideOfText, permutedKey);

            for (int j = 0; j < 8; j++) {
                box = Converter.getCountOfBytes(afterFirstXOR,j*6, 6);
                int x = getValue(box);
                value = Tables.SBOX[j][x];
                System.arraycopy(Converter.byteToBinary(value),0, substitutionChoice,j*4,4);
            }

            permutation = PermutationFunction.permute(initialPattern, substitutionChoice,32);

            afterSecondXOR = XorFunction.xorBytes(leftSideOfText, permutation);

            leftSideOfText = tmp;
            rightSideOfText = afterSecondXOR;
        }

        System.arraycopy(rightSideOfText,0,finalText,0,32);
        System.arraycopy(leftSideOfText,0,finalText,32,32);

        finalText = PermutationFunction.permute(finalPermutationPattern, finalText,64);

        return finalText;
    }


    private void rightExpansionInit(){
        this.extendedRightSideOfText = PermutationFunction.permute(expansionPattern,rightSideOfText,48);
    }

    private int getValue(byte[] text) {
        byte[] row = { text[0], text[5] };
        byte[] column = { text[1], text[2], text[3], text[4] };

        return Converter.binaryToInteget(row) * 16 + Converter.binaryToInteget(column);
    }
}
