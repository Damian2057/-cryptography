package crypto.project.Model.castTypes;

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
}
