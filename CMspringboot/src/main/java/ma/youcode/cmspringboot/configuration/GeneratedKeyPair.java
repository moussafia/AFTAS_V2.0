package ma.youcode.cmspringboot.configuration;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GeneratedKeyPair {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        var keyPair = keyPairGenerator.generateKeyPair();
        byte[] publicKey = keyPair.getPublic().getEncoded();
        byte[] privateKey = keyPair.getPrivate().getEncoded();
        PemWriter pemWriterPub = new PemWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/keys/public.pem")));
        PemObject pemObjectPub = new PemObject("PUBLIC KEY", publicKey);
        pemWriterPub.writeObject(pemObjectPub);
        pemWriterPub.close();
        PemWriter pemWriterPri = new PemWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/keys/private.pem")));
        PemObject pemObjectPri = new PemObject("PRIVATE KEY", privateKey);
        pemWriterPri.writeObject(pemObjectPri);
        pemWriterPri.close();
    }
}
