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

        for(int i = 0; i < plainText.length/8; i++){
            byte[] tmp = XorFunction.xorByteTab(Converter.getCountOfBytes(plainText, i * 8, 8), keyInternal);
           // tmp  = des.encrypt(tmp, keyDes);
           // tmp = Converter.binaryChainToByteForm(tmp);
            tmp = XorFunction.xorByteTab(keyExternal,tmp);
            System.arraycopy(tmp, 0, finalText, i * 8, 8);
        }

        return finalText;
    }

    public byte[] decrypt(byte[] plainText, byte[] keyInternal, byte[] keyDes, byte[] keyExternal) {
        byte[] finalText = new byte[plainText.length];

        for(int i = 0; i < plainText.length/8; i++){
            byte[] tmp = XorFunction.xorByteTab(Converter.getCountOfBytes(plainText, i * 8, 8), keyExternal);
           // tmp = des.decrypt(tmp, keyDes);
            tmp = XorFunction.xorByteTab(keyInternal,tmp);
            System.arraycopy(tmp, 0, finalText, i * 8, 8);
        }

        return finalText;
    }
}
