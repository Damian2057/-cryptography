//
//------------------------------------------------------------
//      DESX Cryptographic Algorithm Implementation Project
//  Damian Biskupski
//  Mateusz Dangreaux
//                          2022
//------------------------------------------------------------
//
package crypto.project.Model.functional;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.patterns.Tables;

public class PermutationFunction {

    /**
     * Method that is permutation of the transmitted data according to the indicated pattern
     * @param pattern, @param block data, @param length, length of data
     * @return Permuted Array
     */

    public static byte[] permutation(byte[] pattern, byte[] block, int length) {
        byte[] blockDestination = new byte[length];
        for (int i = 0; i < length; i++) {
            blockDestination[i] = block[pattern[i]-1];
        }
        return blockDestination;
    }

    /**
     * Method that Convert transferred 48 bits into 32 bits
     * @param binaryBlock Array of Bits
     * @return 32 converted Bit Array
     */

    public static byte[] calculate32BitsSBox(byte[] binaryBlock) {
        byte[] finalResult = new byte[32];
        for (int i = 0; i < 8; i++) {
            //Get the next 6 bits
            byte[] split6bits = get6bits(binaryBlock,i);
            //calculate row value
            int row = getRow(split6bits);
            //calculate Col value
            int col = getCol(split6bits);
            //Get value from address
            byte number = Tables.SBOX[i][row][col];
            //value conversion over 4 bits
            byte[] binaryForm = Converter.byteNumberTo4Bits(number);
            System.arraycopy(binaryForm,0,finalResult,i*4,4);
        }
        return finalResult;
    }

    public static byte[] get6bits(byte[] chain, int round) {
        byte[] bit6 = new byte[6];
        System.arraycopy(chain,round*6, bit6,0,6);
        return bit6;
    }

    public static int getRow(byte[] chain) {
        //get 0 . . . . 5
        return chain[0]*2+ chain[5];
    }

    public static int getCol(byte[] chain) {
        //get . 1 2 3 4 .
        return chain[1]*8+chain[2]*4+chain[3]*2+chain[4];
    }
}
