package crypto.project.Model.castTypes;

public class XorFunction {

    public static byte[] xorBytes(byte[] text, byte[] key) {
        int len = text.length;
        byte[] temp = new byte[len];
        for (int i = 0; i < len; i++) {
            temp[i] = (byte) (text[i] ^ key[i]);
        }
        return temp;
    }

    public static byte[] xorByKey(byte[] text, byte[] key) {
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
