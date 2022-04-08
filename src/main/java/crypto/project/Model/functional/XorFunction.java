package crypto.project.Model.functional;

public class XorFunction {
    public static byte[] xorByteTab(byte[] first, byte[] second) {
        byte[] result = new byte[second.length];

        for (int i = 0; i < second.length; i++) {
            result[i] = (byte) (first[i] ^ second[i]);
        }

        return result;
    }
}
