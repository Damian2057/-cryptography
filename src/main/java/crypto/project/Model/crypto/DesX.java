package crypto.project.Model.crypto;

import crypto.project.Model.castTypes.TypeConverter;
import crypto.project.Model.castTypes.XorFunction;

public class DesX {

    private Des des = new Des();

    public byte[] codeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey) {

        byte[] xorFirst = xorByKey(text,firstXorKey);
        // byte[] des = des.codeText(text, desKey);
        //byte[] xorSecond = xor(des,secondXorKey);

        //return xorSecond;
        return xorFirst;
    }

    public byte[] deCodeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey) {

        return null;
    }

    private byte[] xorByKey(byte[] text, byte[] key) {
        byte[] temp;

        int n = text.length;

        byte[] xorFirst = new byte[n];

        int x = 0;
        int index = 0;
        int count = n;
        for (int i = 0; i < n && count > 0; i++) {
            if(count-8 >= 0) {
                count = count - 8;
                x = 8;
            } else {
                x = count % 8;
                count = count - count % 8;
            }
            temp = XorFunction.xorBytes(TypeConverter.getCountOfBytes(text,i*8,x),key);
            for (int j = 0; j < x; j++) {
                xorFirst[index] = temp[j];
                index++;
            }
        }

        return xorFirst;
    }


}
