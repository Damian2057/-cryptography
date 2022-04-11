package crypto.project.Model.crypto.component;

import crypto.project.Model.functional.PermutationFunction;
import crypto.project.Model.patterns.Tables;

public class KeyBlock {

    private byte[] key56;
    private byte[] key48 = new byte[48];
    private byte[] key64;

    private byte[][] final16SubKeys = new byte[16][48];


    public KeyBlock(byte[] block) {
        this.key64 = block;
        this.key56 = PermutationFunction.permutation(Tables.PC1,key64,56);
    }

    public byte[][] getFinal16SubKeys() {
        for (int i = 0; i < Tables.shiftBits.length; i++) {
            leftShift(Tables.shiftBits[i], i);
        }
        return final16SubKeys;
    }

    public void leftShift(byte count, int subKeyNumber) { // Pracowanie na kopii tablicy nie na niej samej?
        byte tempLeft;
        byte tempRight;
        for (int i = 0; i < count; i++) {
            tempLeft = this.key56[0];
            tempRight = this.key56[28];
            int iter = 27;
            for (int j = 0; j < 27; j++) {
                this.key56[j] = this.key56[j+1];
                this.key56[iter] = this.key56[iter+1];
                iter++;
            }
            this.key56[27] = tempLeft;
            this.key56[55] = tempRight;
        }
        key48 = PermutationFunction.permutation(Tables.PC2,this.key56,48);
        for (int j = 0; j < 48; j++) {
            final16SubKeys[subKeyNumber][j] = key48[j];
        }
    }

}
