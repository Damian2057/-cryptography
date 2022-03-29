package crypto.project.Model.castTypes;

import java.util.Arrays;

public class TypeConverter {

    public static byte[] stringToByteTab(String text) {
        byte[] temp = new byte[text.length()];
        for (int i = 0; i < text.length(); i++) {
            temp[i] = (byte) text.charAt(i);
        }
        return temp;
    }

    public static String byteTabToString(byte[] tab) {
        StringBuilder temp = new StringBuilder();
        int len = tab.length;
        for (int i = 0; i < len; i++) {
            temp.append((char) tab[i]);
        }
        return temp.toString();
    }

    public static byte[] getCountOfBytes(byte[] text, int index, int count) {
        byte[] temp = new byte[count];
        for (int i = 0; i < count; i++) {
            temp[i] = text[index];
            index++;
        }
        return temp;
    }

    public static byte[] completeTheBits(byte[] bytes) {
        int numberOfBytes = bytes.length;
        byte howMany = 0;
        while (numberOfBytes % 8 != 0) {
            numberOfBytes++;
            howMany++;
        }
        byte[] filled = new byte[numberOfBytes];
        System.arraycopy(bytes, 0, filled, 0, bytes.length);
        if(howMany != 0) {
            howMany--;
            Arrays.fill(filled,bytes.length, numberOfBytes, (byte) 0);
            filled[filled.length-1] = howMany;
        }
        return filled;
    }

    public static byte[] cutLastBytes(byte[] bytes) {
        int howMany = bytes[bytes.length-1];
        int size = bytes.length-howMany-1;
        if(howMany > 31 || howMany < 0) {
            return bytes;
        }

        byte[] filled = new byte[size];

        System.arraycopy(bytes, 0, filled,0,size);

        return filled;
    }
}
