package trax.aero.Encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encryption
{
	private static final String encryptionKey = "i/Mfi3u3jHFR0OSapCIdtA==";

	/**
	 * encrypt/decrypt a string using BouncyCastleProvider
	 * 
	 * @throws NoSuchPaddingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws ShortBufferException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @param str
	 * @param encrypt - true: encryption encrypt - false: decryption
	 * 
	 */
	public static String cryptoControl(String str, boolean encrypt)
			throws NoSuchAlgorithmException, NoSuchProviderException,
			NoSuchPaddingException, InvalidKeyException, ShortBufferException,
			IllegalBlockSizeException, BadPaddingException
	{
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		byte[] keyBytes = null;
		byte[] cipherText = null;
		byte[] plainText = null;
		int ctLength = 0;
		if (str == null)
		{
			str = "";
		}
		str = str.trim();
		if (str.length() > 0)
		{
			keyBytes = Base64.decodeBase64(encryptionKey.getBytes());
			final SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
			final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding",
					"BC");
			if (encrypt)
			{
				cipher.init(Cipher.ENCRYPT_MODE, key);
				final byte[] input = str.getBytes();
				cipherText = new byte[cipher.getOutputSize(input.length)];
				ctLength = cipher.update(input, 0, input.length, cipherText, 0);
				ctLength += cipher.doFinal(cipherText, ctLength);
				str = new String(Base64.encodeBase64(cipherText));
			} else
			{
				cipher.init(Cipher.DECRYPT_MODE, key);
				cipherText = Base64.decodeBase64(str.getBytes());
				ctLength = cipherText.length;
				plainText = new byte[cipher.getOutputSize(ctLength)];
				int ptLength = cipher.update(cipherText, 0, ctLength,
						plainText, 0);
				ptLength += cipher.doFinal(plainText, ptLength);
				str = new String(plainText).substring(0, ptLength);
			}
		}
		return str;
	}

	public static void encryptFile(File inputFile, File outputFile)
			throws NoSuchAlgorithmException, NoSuchProviderException,
			NoSuchPaddingException, InvalidKeyException, ShortBufferException,
			IllegalBlockSizeException, BadPaddingException, IOException {
		doCrypto(Cipher.ENCRYPT_MODE, inputFile, outputFile);
	}

	public static void decryptFile(File inputFile, File outputFile)
			throws NoSuchAlgorithmException, NoSuchProviderException,
			NoSuchPaddingException, InvalidKeyException, ShortBufferException,
			IllegalBlockSizeException, BadPaddingException, IOException {
		doCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile);
	}

	private static void doCrypto(int cipherMode, File inputFile,
			File outputFile) throws NoSuchAlgorithmException, NoSuchProviderException,
	NoSuchPaddingException, InvalidKeyException, ShortBufferException,
	IllegalBlockSizeException, BadPaddingException, IOException {

		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		byte[] keyBytes = null;
		
		keyBytes = Base64.decodeBase64(encryptionKey.getBytes());
		final SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding",
				"BC");
		cipher.init(cipherMode, key);

		FileInputStream inputStream = new FileInputStream(inputFile);
		byte[] inputBytes = new byte[(int) inputFile.length()];
		inputStream.read(inputBytes);

		byte[] outputBytes = cipher.doFinal(inputBytes);

		FileOutputStream outputStream = new FileOutputStream(outputFile);
		outputStream.write(outputBytes);

		inputStream.close();
		outputStream.close();

	}
}
