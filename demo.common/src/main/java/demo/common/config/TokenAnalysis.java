package demo.common.config;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Zml
 * @createDate 2021-11-12
 */
public class TokenAnalysis implements Serializable {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private SecretKeySpec key;
    private StringRedisTemplate redisTemplate;

    public TokenAnalysis(String k){
        key = new SecretKeySpec(k.getBytes(), KEY_ALGORITHM);
    }
    /**
     * 分析token
     * @param token
     */
    public void analysisToken(String token){
        token = token.replace("%2B", "+").replace("%2A", "*").replace("%20", " ");
        String s = decryptOralce(token);
        String[] split = s.split("&");
        Integer accountId = Integer.valueOf(split[0]);
        String nickName = split[1];
        Boolean timeStamp = Boolean.valueOf(split[2]);
        Integer randomCode = Integer.valueOf(split[3]);
        //验证账户登录是否过期
        String s1 = redisTemplate.opsForValue().get("System:Token:Account:"+accountId);
        if (s1 == null){
            timeStamp = true;
        }else{
            timeStamp = false;
        }
        //判断账户是否过期

    }


    /**
     * AES解密
     * @param text
     */
    public String decryptOralce(String text){
        //实例化
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(text));
            //执行操作
            byte[] result = cipher.doFinal(key.getEncoded());
            return new String(result, "utf-8").replace("\0", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成加密秘钥
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    private static SecretKey getSecretKey(final String key) throws UnsupportedEncodingException {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        String charset = "utf-8";
        SecureRandom random = new SecureRandom(key.getBytes());
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, random);

            //生成一个密钥
//            return new SecretKeySpec(Arrays.copyOf(key.getBytes("utf-8"), 16), KEY_ALGORITHM);// 转换为AES专用密钥
            return kg.generateKey();// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * str不是16的倍数那就补足为16的倍数
     * @param value
     */
    public String addTo16(String value){
        while (value.length() % 16 !=0) {
            value += "\0";
        }
        return value;
    }

    /**
     * AES加密
     */
    public String encryptOracle(byte[] data){
        Cipher cipher ;
        try {
            cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(data), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        TokenAnalysis analysis = new TokenAnalysis("ceecafac303ff92c6857e3b92f06174e9a8b66df7d63db554629976586b17a34975480de0109afa0540b434e5bd8586e");
        String ds = analysis.decryptOralce("ceecafac303ff92c6857e3b92f06174e9a8b66df7d63db554629976586b17a34975480de0109afa0540b434e5bd8586e");
        System.out.println(ds);
    }
}
