package crypto.project.Model.crypto;

import crypto.project.Model.castTypes.XorFunction;

public class DesX implements Algorithm {

    private final Des des = new Des();

    @Override
    public byte[] codeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey) {

        byte[] textAfterFirstXOR = XorFunction.xorByKey(text,firstXorKey);
        //byte[] textAfterDES = des.codeText(textAfterFirstXOR, desKey);
        //byte[] textAfterSecondXOR = xor(textAfterDES,secondXorKey);

        //return textAfterSecondXOR;
        return textAfterFirstXOR;
    }

    @Override
    public byte[] deCodeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey) {

        byte[] textAfterFirstXOR = XorFunction.xorByKey(text,firstXorKey); //later exchange firstXorKey to secondXorKey!!
        //byte[] textAfterDES = des.codeText(textAfterFirstXOR, desKey);
        //byte[] textAfterSecondXOR = xor(textAfterDES,firstXorKey);

        //return textAfterSecondXOR;
        return textAfterFirstXOR;
    }
}
