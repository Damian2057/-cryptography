package crypto.project.Model.functional;

public class XorFunction {

    public static byte[] xorBytes(byte[] text, byte[] key) {
        int len = text.length;
        byte[] temp = new byte[len];
        for (int i = 0; i < len; i++) {
            temp[i] = (byte) (text[i] ^ key[i]);
        }
        return temp;
    }
}
