package eldertrack.db;

import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Crypto {
	
	/**
	 * @param key - Key used to encrypt the text
	 * @param initVector - Initialization vector used 
	 * @param text - Text to be encrypted
	 * @return
	 */
    public static String encrypt(char[] password, byte[] salt,  String initVector, String text) {
        try {
        	
        	// Get key from password provided
        	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        	KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        	SecretKey tmp = factory.generateSecret(spec);
        	SecretKeySpec secret = new SecretKeySpec(tmp.getEncoded(), "AES");
        	
        	// Get byte array from IV
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            
            // Magical encryption happens here (AES256)
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secret, iv);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            
            System.out.println("Encrypted: " + Base64.encodeBase64String(encrypted));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] decrypt(char[] password, byte[] salt, String initVector, String encrypted) throws BadPaddingException {
        try {
        	
        	// Get key from password provided
           	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        	KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        	SecretKey tmp = factory.generateSecret(spec);
        	SecretKeySpec secret = new SecretKeySpec(tmp.getEncoded(), "AES");
        	
        	// Get byte array from IV
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            
            /// Magical cryptography happens here (AES256)
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secret, iv);

            return cipher.doFinal(Base64.decodeBase64(encrypted));

        } catch (BadPaddingException e) {
        	throw e;
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String keyE = "12345"; // KEY for ENCRYPTION
        String initVectorE = "ElderTRACK451462"; // 16 bytes IV
        String keyD = "12345"; // KEY for DECRYPTION
        String initVectorD = "ElderTRACK451462"; // 16 bytes IV
    	byte[] salt = {0x1F, 0x4E, 0x4D, 0x7A, 0x22, 0x31, 0x64, 0x5C, 0x1A};
        
        String encrypted = encrypt(keyE.toCharArray(), salt, initVectorE, "HELLO WORLDY");
        String decrypted;
		try {
			decrypted = new String(decrypt(keyD.toCharArray(), salt, initVectorD, encrypted));
	        System.out.println(decrypted);
		} catch (BadPaddingException e) {
			System.out.println("Incorrect decryption key used.");
		} finally {
			
		}
    }
}