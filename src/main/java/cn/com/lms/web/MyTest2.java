package cn.com.lms.web;

import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;

public class MyTest2 {
    /** 算法名称 */
    private static final String ALGORITHOM = "RSA";

    /** 密钥大小 */
    private static final int KEY_SIZE = 1024;
    /** 默认的安全服务提供者 */
//    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    private static KeyPairGenerator keyPairGen = null;
    private static KeyFactory keyFactory = null;
    /** 缓存的密钥对。 */
    private static KeyPair oneKeyPair = null;

    private static String radamKey = "";

    static {
        try {
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM);
            keyFactory = KeyFactory.getInstance(ALGORITHOM);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 生成并返回RSA密钥对。
     */
    private static synchronized KeyPair generateKeyPair() {
        try {
            keyPairGen.initialize(KEY_SIZE,
                    new SecureRandom(radamKey.getBytes()));
            oneKeyPair = keyPairGen.generateKeyPair();
            return oneKeyPair;
        } catch (InvalidParameterException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /** 返回已初始化的默认的公钥。 */
    public static RSAPublicKey getDefaultPublicKey() {
        KeyPair keyPair = generateKeyPair();
        if (keyPair != null) {
            return (RSAPublicKey) keyPair.getPublic();
        }
        return null;
    }
    public static void main(String[] args) {
    	System.out.println(getDefaultPublicKey());	
	}
    
}
