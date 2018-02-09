package com.beyondskool.util;

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
import com.beyondskool.dataimport.ExceptionConstants;
import com.beyondskool.exception.DataImportException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class DecryptionUtil {

	public static final Logger DEBUG_LOGGER = Logger.getLogger("debuglogging");
	public static final Logger ERROR_LOGGER = Logger.getLogger("errorlogging");

	/**
	 * Decrypt.
	 * 
	 * @param encryptedText
	 *            the encrypted text
	 * @return the string
	 * @throws DataImportException
	 *             the data import exception
	 */
	public static String decrypt(final String encryptedText)
			throws DataImportException {

		DEBUG_LOGGER.log(Level.DEBUG,
				"DecryptionUtil: decryptPassword method started");

		String decryptedText = null;
		try {
			byte[] bufferKey = Constants.ENCODE_KEY
					.getBytes(Constants.UNICODE_FORMAT);
			KeySpec keySpec = new DESedeKeySpec(bufferKey);
			SecretKeyFactory skf = SecretKeyFactory
					.getInstance(Constants.ENCODE_SCHEME);
			Cipher cipher = Cipher.getInstance(Constants.ENCODE_SCHEME);
			SecretKey key = skf.generateSecret(keySpec);
			cipher.init(Cipher.DECRYPT_MODE, key);
			final byte[] bufferInput = Base64.decodeBase64(encryptedText);
			final byte[] bufferOutput = cipher.doFinal(bufferInput);
			decryptedText = new String(bufferOutput);
		} catch (UnsupportedEncodingException | InvalidKeyException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeySpecException | IllegalBlockSizeException
				| BadPaddingException exception) {
			ERROR_LOGGER.log(Level.ERROR,
					"DecryptionUtil: decrypt - Unable to decrypt given text : "
							+ encryptedText);
			throw new DataImportException(ExceptionConstants.EXCEPTION_CODE,
					exception);

		}
		DEBUG_LOGGER.log(Level.DEBUG,
				"DecryptionUtil: decryptPassword method ends");
		return decryptedText;
	}
}
