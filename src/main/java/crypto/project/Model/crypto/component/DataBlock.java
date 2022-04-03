package crypto.project.Model.crypto.component;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.functional.PermutationFunction;
import crypto.project.Model.patterns.Tables;

public class DataBlock {

    private final byte[] text;
    private byte[] textLeft = new byte[32];
    private byte[] textRight = new byte[32];
    private byte[] finalForm;

    public DataBlock(byte[] text) {
        this.text = text;
        finalForm = PermutationFunction.permute(Tables.IP, Converter.toBinaryTab(text),64);
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
