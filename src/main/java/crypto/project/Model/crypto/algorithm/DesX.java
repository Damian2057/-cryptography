//
//------------------------------------------------------------
//      DESX Cryptographic Algorithm Implementation Project
//  Damian Biskupski
//  Mateusz Dangreaux
//                          2022
//------------------------------------------------------------
//
package crypto.project.Model.crypto.algorithm;

import crypto.project.Model.castTypes.Converter;
import crypto.project.Model.functional.XorFunction;

public class DesX implements Algorithm {

    public byte[] encrypt(byte[] plainText, byte[] keyInternal, byte[] keyDes, byte[] keyExternal) {
        Des des = new Des();
        byte[] finalText = new byte[plainText.length];
        for(int i = 0; i < plainText.length/8; i++){
            //The block is Xor-processed
            byte[] tmp = XorFunction.xor(Converter.getCountOfBytes(plainText, i * 8, 8), keyInternal);
            //The block is DES-processed
            tmp  = des.encrypt(tmp, keyDes);
            //The block is again Xor-processed
            tmp = XorFunction.xor(keyExternal,tmp);
            //rewriting the block (8 bytes) to the resulting Array
            System.arraycopy(tmp, 0, finalText, i * 8, 8);
        }
        return finalText;
    }

    public byte[] decrypt(byte[] plainText, byte[] keyInternal, byte[] keyDes, byte[] keyExternal) {
        Des des = new Des();
        byte[] finalText = new byte[plainText.length];
        for(int i = 0; i < plainText.length/8; i++){
            //The block is Xor-processed
            byte[] tmp = XorFunction.xor(Converter.getCountOfBytes(plainText, i * 8, 8), keyExternal);
            //The block is DES-processed
            tmp = des.decrypt(tmp, keyDes);
            //The block is again Xor-processed
            tmp = XorFunction.xor(keyInternal,tmp);
            //rewriting the block (8 bytes) to the resulting Array
            System.arraycopy(tmp, 0, finalText, i * 8, 8);
        }
        return finalText;
    }
}
