package crypto.project.Model.crypto.algorithm;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.functional.XorFunction;

public class DesX implements Algorithm {

    private final Des des = new Des();

    @Override
    public byte[] codeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey) {
        int size = text.length /8;
        byte[] finalForm = new byte[text.length];
        for (int i = 0; i < size; i++) {
            byte[] temp = Converter.getCountOfBytes(text,i*8,8);
            temp = XorFunction.xorBytes(temp,firstXorKey);
            temp = des.codeText(temp,desKey);
            temp = XorFunction.xorBytes(Converter.binaryChainToByteForm(temp), secondXorKey);
            System.arraycopy(temp, 0, finalForm, i*8, temp.length);
        }

        return finalForm;
    }

    @Override
    public byte[] deCodeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey) {
        int size = text.length / 8;
        byte[] finalForm = new byte[text.length];
        for (int i = 0; i < size; i++) {
            byte[] temp = Converter.getCountOfBytes(text, i * 8, 8);
            temp = XorFunction.xorBytes(temp, secondXorKey);
            temp = des.decodeText(temp,desKey);
            temp = XorFunction.xorBytes(Converter.binaryChainToByteForm(temp), firstXorKey);
            System.arraycopy(temp, 0, finalForm, i * 8, temp.length);
        }

        return finalForm;
    }
}
