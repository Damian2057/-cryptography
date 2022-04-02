package crypto.project.Model.functional;

public class PermutationFunction {
    public static byte[] permute(byte[] template, byte[] blockOfText, int size) {
        byte[] result = new byte[size];
        for (int i = 0; i < size; i++) {
            int x = template[i]-1;
            result[i] = blockOfText[x];
        }
        return result;
    }
}
