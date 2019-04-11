package ld00245_chatclient_com1028;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encrypter {

	private final static String ALGORITHM = "AES";
	private final static byte[] ENCRYPTION_KEY = new byte[] { 'H', 'e', 'r', 'o', 'e', 's', ' ', 'n', 'e', 'v', 'e',
			'r', ' ', 'd', 'i', 'e' };
	/**
	 * A method used for encrypting the message passed in the parameter
	 * @param message Message prior to encryption
	 * @return An encrypted message in string format
	 * @throws Exception
	 */
	public static String encrypt(String message) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encValue = cipher.doFinal(message.getBytes());
		String encryptedValue = new String(new Base64().encode(encValue));
		return encryptedValue;
	}
	/**
	 * A method used for decrypting the messages passed in the parameter
	 * @param encryptedMessage	previously encrypted message
	 * @return	a decrypted value of the previously encrypted message, in string format
	 * @throws Exception
	 */
	public static String decrypt(String encryptedMessage) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = new Base64().decode(encryptedMessage.getBytes());
		byte[] decValue = cipher.doFinal(decodedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
	/**
	 * A method which uses the algorithm and encryption key to generate a key to cipher with
	 * @return Returns the generated key
	 * @throws Exception
	 */
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(ENCRYPTION_KEY, ALGORITHM);
		return key;

	}
}
