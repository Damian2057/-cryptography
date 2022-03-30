package crypto.project.Model.crypto;

import crypto.project.Model.castTypes.TypeConverter;
import crypto.project.Model.castTypes.XorFunction;

public class DesX implements Algorithm {

    private Des des = new Des();

    @Override
    public byte[] codeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey) {

        byte[] xorFirst = XorFunction.xorByKey(text,firstXorKey);
        // byte[] des = des.codeText(text, desKey);
        //byte[] xorSecond = xor(des,secondXorKey);

        //return xorSecond;
        return xorFirst;
    }

    @Override
    public byte[] deCodeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey) {

        return null;
    }
}
