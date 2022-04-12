//
//------------------------------------------------------------
//      DESX Cryptographic Algorithm Implementation Project
//  Damian Biskupski
//  Mateusz Dangreaux
//                          2022
//------------------------------------------------------------
//
package crypto.project.Model.crypto.algorithm;

public interface Algorithm {
    byte[] encrypt(byte[] plainText, byte[] keyInternal, byte[] keyDes, byte[] keyExternal);
    byte[] decrypt(byte[] plainText, byte[] keyInternal, byte[] keyDes, byte[] keyExternal);
}
