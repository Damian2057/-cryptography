package crypto.project.Model.crypto.component;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.functional.PermutationFunction;

public class DataBlock {

    private final byte[] text;
    private byte[] textLeft = new byte[32];
    private byte[] textRight = new byte[32];
    private byte[] finalForm;
    private final byte[] initialPattern = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17,  9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };

    public DataBlock(byte[] text) {
        this.text = text;
        finalForm = PermutationFunction.permute(initialPattern, Converter.toBinaryTab(text),64);
        divideTextBlockIntoTwoPages(finalForm);
    }

    private void divideTextBlockIntoTwoPages(byte[] source) {
        System.arraycopy(source, 0, textLeft, 0, 32);
        System.arraycopy(source, 32, textRight, 0, 32);
    }

    public byte[] getTextLeft() {
        return textLeft;
    }

    public byte[] getTextRight() {
        return textRight;
    }
}
