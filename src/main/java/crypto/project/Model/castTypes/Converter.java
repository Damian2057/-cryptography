package crypto.project.Model.castTypes;

import java.util.Arrays;

public class Converter {

    /**
     * Method that is replacing a string with a byte
     * @param bytes normal ASCII Text
     * @return byte Table
     */

    public static byte[] stringToByteTab(String bytes) {
        byte[] temp = new byte[bytes.length()];
        for (int i = 0; i < bytes.length(); i++) {
            temp[i] = (byte) bytes.charAt(i);
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
            Arrays.fill(filled,bytes.length, numberOfBytes, (byte) 0);
            filled[filled.length-1] = howMany;
        }
        return filled;
    }

    public static byte[] cutLastBytes(byte[] bytes) {
        int howMany = bytes[bytes.length-1];
        int size = bytes.length-howMany;
        if(howMany > 31 || howMany < 0) {
            return bytes;
        }

        byte[] filled = new byte[size];

        System.arraycopy(bytes, 0, filled,0,size);

        return filled;
    }

    public static String byteTabToString(byte[] bytes) {
        StringBuilder temp = new StringBuilder();
        int len = bytes.length;
        for (int i = 0; i < len; i++) {
            temp.append((char) bytes[i]);
        }
        return temp.toString();
    }

    public static byte[] getCountOfBytes(byte[] bytes, int index, int count) {
        byte[] temp = new byte[count];
        for (int i = 0; i < count; i++) {
            temp[i] = bytes[index];
            index++;
        }
        return temp;
    }

    public static byte[] byteNumberTo4Bits(byte number) {
        //SBOX - 6 BitsSplit
        byte[] blockByte = new byte[4];
        byte tmp;

        for (int i = 0; i < 4; i++) {
            blockByte[i] = (byte) (number%2);
            number = (byte) (number/2);
        }

        for(int i = 0; i < 2; i++){
            tmp = blockByte[i];
            blockByte[i] = blockByte[3 - i];
            blockByte[3 - i] = tmp;
        }

        return blockByte;
    }

    public static byte[] binaryChainToByteForm(byte[] bits) {
        int iterator = 0;
        byte[] finalForm = new byte[8];
        for (int i = 0; i < 8; i++) {
            int upperLimit = 128;
            for (int j = 0; j < 8; j++) {
                if (bits[iterator] == 1) {
                    finalForm[i] += (byte) upperLimit;
                }
                iterator++;
                upperLimit /= 2;
            }
        }
        return finalForm;
    }

    public static byte[] byteTabToBinary(byte[] bytes) {
        int iterator = 0;
        byte[] finalForm = new byte[64];

        for (int i = 0; i < 8; i++) {
            byte[] bits8 = byteTo8BitTable(bytes[i]);
            for (int j = 0; j < 8; j++) {
                finalForm[iterator++] = bits8[j];
            }
        }
        return finalForm;
    }

    public static byte[] byteTo8BitTable(byte chunk) {
        byte[] finalForm = new byte[8];
        int number = chunk;
        if (number < 0) {
            number = number * (-1);
            for (int i = 7; i >= 0; i--) {
                finalForm[i] = (byte) (number % 2 == 1 ? 1 : 0);
                number = number / 2;
            }
            for (int i = 0; i < 8; i++) {
                finalForm[i] ^= 1;
            }
            for (int i = 7; i >= 0; i--) {
                if (finalForm[i] == 0) {
                    finalForm[i] = 1;
                    break;
                }
                finalForm[i] = 0;
            }
        } else {
            for (int i = 7; i >= 0; i--) {
                finalForm[i] = (byte) (number % 2 == 1 ? 1 : 0);
                number /= 2;
            }
        }
        return finalForm;
    }

}
