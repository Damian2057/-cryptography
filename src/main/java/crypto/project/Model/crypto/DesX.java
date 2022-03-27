package crypto.project.Model.crypto;

import crypto.project.Model.castTypes.TypeConverter;
import crypto.project.Model.castTypes.XorFunction;

public class DesX {

    private Des des = new Des();
    private byte[] codedText;
    private byte[] decodedText;

    public byte[] codeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey) {

        byte[] xorFirst = xor(text,firstXorKey);
            // byte[] des = null;
        //byte[] xorSecond = xor(des,secondXorKey);

        //return xorSecond;
        return xorFirst;
    }

    public byte[] deCodeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey) {

        return null;
    }

    private byte[] xor(byte[] text, byte[] key) {
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
