package com.beyondskool.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beyondskool.helper.Constants;


public class EncryptionUtil {
	
	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");
	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");
	
	public static String encryptString(String input) throws Exception {
		String encryptedString = encrypt(input);
		return encryptedString;
		
	}

	/**
	 * Encrypt.
	 * 
	 * @param inputText
	 *            the unencrypted string
	 * @return the string
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws InvalidKeyException
	 *             the invalid key exception
	 * @throws IllegalBlockSizeException
	 *             the illegal block size exception
	 * @throws BadPaddingException
	 *             the bad padding exception
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws InvalidKeySpecException
	 *             the invalid key spec exception
	 * @throws NoSuchPaddingException
	 *             the no such padding exception
	 */
	public static String encrypt(final String inputText)
			throws UnsupportedEncodingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException {
		
		DEBUG_LOGGER.log(Level.DEBUG,
				"EncryptionUtil: encrypt method started");
		
		String encryptedText = null;
		final byte[] bufferKey = Constants.ENCODE_KEY
				.getBytes(Constants.UNICODE_FORMAT);
		final KeySpec keySpec = new DESedeKeySpec(bufferKey);
		final SecretKeyFactory skf = SecretKeyFactory
				.getInstance(Constants.ENCODE_SCHEME);
		final Cipher cipher = Cipher.getInstance(Constants.ENCODE_SCHEME);
		final SecretKey key = skf.generateSecret(keySpec);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		final byte[] bufferInput = inputText.getBytes(Constants.UNICODE_FORMAT);
		final byte[] bufferOutput = cipher.doFinal(bufferInput);
		encryptedText = new String(Base64.encodeBase64(bufferOutput));
		
		DEBUG_LOGGER.log(Level.DEBUG,
				"EncryptionUtil: encrypt method ended");
		
		return encryptedText;
	}
	
	public static void main(String args[]) throws UnsupportedEncodingException, InvalidKeyException,
	IllegalBlockSizeException, BadPaddingException,
	NoSuchAlgorithmException, InvalidKeySpecException,
	NoSuchPaddingException{
	{			
			DEBUG_LOGGER.log(Level.DEBUG,
					"EncryptionUtil: encrypt method started");
			String inputText = "admin";
			String encryptedText = null;
			final byte[] bufferKey = Constants.ENCODE_KEY
					.getBytes(Constants.UNICODE_FORMAT);
			final KeySpec keySpec = new DESedeKeySpec(bufferKey);
			final SecretKeyFactory skf = SecretKeyFactory
					.getInstance(Constants.ENCODE_SCHEME);
			final Cipher cipher = Cipher.getInstance(Constants.ENCODE_SCHEME);
			final SecretKey key = skf.generateSecret(keySpec);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			final byte[] bufferInput = inputText.getBytes(Constants.UNICODE_FORMAT);
			final byte[] bufferOutput = cipher.doFinal(bufferInput);
			encryptedText = new String(Base64.encodeBase64(bufferOutput));
			
			DEBUG_LOGGER.log(Level.DEBUG,
					"EncryptionUtil: encrypt method ended");
			
			System.out.println(encryptedText);
		}
	}
}