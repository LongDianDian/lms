package cn.com.lms.web;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class MyTest {
	
    /**  
     * 加密算法RSA  
     */  
    public static final String KEY_ALGORITHM = "RSA";  
  
    /**  
     * 签名算法  
     */  
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";  
  
    /**  
     * 获取公钥的key  
     */  
    private static final String PUBLIC_KEY = "RSAPublicKey";  
  
    /**  
     * 获取私钥的key  
     */  
    private static final String PRIVATE_KEY = "RSAPrivateKey";  
  
    /**  
     * RSA最大加密明文大小  
     */  
    private static final int MAX_ENCRYPT_BLOCK = 117;  
  
    /**  
     * RSA最大解密密文大小  
     */  
    private static final int MAX_DECRYPT_BLOCK = 128;  
    public static Map<String, Object> genKeyPair() throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator  
                .getInstance(KEY_ALGORITHM);  
//        keyPairGen.initialize(1024);  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        Map<String, Object> keyMap = new HashMap<String, Object>(2);  
        keyMap.put(PUBLIC_KEY, publicKey);  
        keyMap.put(PRIVATE_KEY, privateKey);  
        return keyMap;  
    } 
    public static void main(String[] args) throws Exception {
		Map<String,Object> map = genKeyPair();
		System.out.println(map.get("PUBLIC_KEY"));
	}
}
