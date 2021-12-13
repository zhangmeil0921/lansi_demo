package demo.common.work.wexin;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import javax.crypto.Cipher;
import java.io.Reader;
import java.io.StringReader;
import java.security.PrivateKey;
import java.security.Security;

/**
 * @author ZML
 * @version 1.0
 * @date 2021/12/2 2:19 下午
 */
public class RSAEncrypt {

    public static String decryptRSA(String str, String privateKey) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        rsa.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
        byte[] utf8 = rsa.doFinal(Base64.decodeBase64(str));
        String result = new String(utf8,"UTF-8");
        return result;
    }

    public static PrivateKey getPrivateKey (String privateKey) throws Exception {
        Reader privateKeyReader = new StringReader(privateKey);
        PEMParser privatePemParser = new PEMParser(privateKeyReader);
        Object privateObject = privatePemParser.readObject();
        if (privateObject instanceof PEMKeyPair) {
            PEMKeyPair pemKeyPair = (PEMKeyPair) privateObject;
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            PrivateKey privKey = converter.getPrivateKey(pemKeyPair.getPrivateKeyInfo());
            return privKey;
        }
        return null;
    }
}