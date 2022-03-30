package crypto.project.Model.crypto;

public interface Algorithm {
    byte[] codeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey);
    byte[] deCodeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey);
}
