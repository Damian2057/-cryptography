package crypto.project.Model.crypto.algorithm;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.functional.XorFunction;

public class DesX implements Algorithm {

    private final Des des;

    public DesX() {
        des = new Des();
    }

    public byte[] encrypt(byte[] plainText, byte[] keyInternal, byte[] keyDes, byte[] keyExternal) {
        byte[] finalText = new byte[plainText.length];
        byte[] binaryText = Converter.toBinaryTab(plainText);
        byte[] binarykeyInternal = Converter.toBinaryTab(keyInternal);
        byte[] binarykeyDes = Converter.toBinaryTab(keyDes);
        byte[] binarykeyExternal = Converter.toBinaryTab(keyExternal);

        for(int i = 0; i < plainText.length/8; i++){
            byte[] tmp = XorFunction.xor(Converter.getCountOfBytes(binaryText, i * 64, 64), binarykeyInternal);
             tmp  = des.encrypt(tmp, binarykeyDes);
            tmp = XorFunction.xor(binarykeyExternal,tmp);
            tmp = Converter.binaryChainToByteForm(tmp);
            System.arraycopy(tmp, 0, finalText, i * 8, 8);
        }

        return finalText;
    }

    public byte[] decrypt(byte[] plainText, byte[] keyInternal, byte[] keyDes, byte[] keyExternal) {
        byte[] finalText = new byte[plainText.length];
        byte[] binaryText = Converter.toBinaryTab(plainText);
        byte[] binarykeyInternal = Converter.toBinaryTab(keyInternal);
        byte[] binarykeyDes = Converter.toBinaryTab(keyDes);
        byte[] binarykeyExternal = Converter.toBinaryTab(keyExternal);
        for(int i = 0; i < plainText.length/8; i++){
            byte[] tmp = XorFunction.xor(Converter.getCountOfBytes(binaryText, i * 64, 64), binarykeyExternal);
            tmp = des.decrypt(tmp, binarykeyDes);
            tmp = XorFunction.xor(binarykeyInternal,tmp);
            tmp = Converter.binaryChainToByteForm(tmp);
            System.arraycopy(tmp, 0, finalText, i * 8, 8);
        }

        return finalText;
    }
}
