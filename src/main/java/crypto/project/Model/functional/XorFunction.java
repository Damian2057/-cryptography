//
//------------------------------------------------------------
//      DESX Cryptographic Algorithm Implementation Project
//  Damian Biskupski
//  Mateusz Dangreaux
//                          2022
//------------------------------------------------------------
//
package crypto.project.Model.functional;

public class XorFunction {

    /**
     * Method that is performing XOR operations
     * @param first, @param second they must be the same length
     * @return xor result
     */

    public static byte[] xor(byte[] first, byte[] second) {
        byte[] result = new byte[second.length];
        for (int i = 0; i < second.length; i++) {
            result[i] = (byte) (first[i] ^ second[i]);
        }
        return result;
    }
}
