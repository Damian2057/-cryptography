package crypto.project.Model.crypto;

public interface Algorithm {
    public byte[] codeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey);
    public byte[] deCodeText(byte[] text, byte[] firstXorKey, byte[] desKey, byte[] secondXorKey);
}
