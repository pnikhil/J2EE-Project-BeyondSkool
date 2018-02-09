package com.beyondskool.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class TokenUtil {
	static final ResourceBundle Dir_ResourceBundle = ResourceBundle.getBundle("Common");

	public static String createToken(String userName, String Role) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		String strPassPhrase = Dir_ResourceBundle.getString("PASS_PHRASE");
		String secretKey = Dir_ResourceBundle.getString("SECRET_KEY");
		String issuer = Dir_ResourceBundle.getString("ISSUER");
		String encryptedKey = null;
		String token = null;
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("DESede");
			SecretKey key = factory.generateSecret(new DESedeKeySpec(strPassPhrase.getBytes()));
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encryptedKey = DatatypeConverter.printBase64Binary(cipher.doFinal(secretKey.getBytes()));
			cipher.init(Cipher.DECRYPT_MODE, key);
			String decryptedKey = new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(encryptedKey)));
			Calendar currentDate = Calendar.getInstance();
			long dateInCal = currentDate.getTimeInMillis();
			Date issuedAt = currentDate.getTime();
			Date expiry = new Date(
					dateInCal + (Integer.valueOf(Dir_ResourceBundle.getString("SESSION_EXPIRY_DURATION"))));
			token = Jwts.builder().claim("userRole", Role).setSubject(userName).setIssuer(issuer)
					.setIssuedAt(issuedAt).setExpiration(expiry).signWith(signatureAlgorithm, encryptedKey).compact();
			
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return token;
	}

	public static String refreshToken(String token) {
		SecretKeyFactory factory;
		String encryptedKey;
		String strPassPhrase = Dir_ResourceBundle.getString("PASS_PHRASE");
		String secretKey = Dir_ResourceBundle.getString("SECRET_KEY");
	
		try {
			factory = SecretKeyFactory.getInstance("DESede");
			SecretKey key;
			key = factory.generateSecret(new DESedeKeySpec(strPassPhrase.getBytes()));
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			Calendar currentDate = Calendar.getInstance();
			long dateInCal = currentDate.getTimeInMillis();
			Date issuedAt = currentDate.getTime();
			Date expiry = new Date(
					dateInCal + (Integer.valueOf(Dir_ResourceBundle.getString("SESSION_EXPIRY_DURATION"))));
			encryptedKey = DatatypeConverter.printBase64Binary(cipher.doFinal(secretKey.getBytes()));
			Claims claims = Jwts.parser().setSigningKey(encryptedKey).parseClaimsJws(token).getBody();
			claims.setIssuedAt(issuedAt);
			claims.setExpiration(expiry);
		}catch (NoSuchAlgorithmException | InvalidKeyException e) {
			
		} catch (IllegalBlockSizeException e) {
			
		} catch (BadPaddingException e) {
			
		} catch (NoSuchPaddingException e) {
			
		} catch (InvalidKeySpecException e) {
			
		}
		return token;
	}

	public Map<String, Claims> validateToken(String token) throws InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
		SecretKeyFactory factory;
		String encryptedKey;
		// Validate the token, extract the user id and role and set to the map.
		String strPassPhrase = Dir_ResourceBundle.getString("PASS_PHRASE");
		String secretKey = Dir_ResourceBundle.getString("SECRET_KEY");
		Map<String, Claims> userData = null;
		try {
			factory = SecretKeyFactory.getInstance("DESede");
			SecretKey key;
			key = factory.generateSecret(new DESedeKeySpec(strPassPhrase.getBytes()));
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encryptedKey = DatatypeConverter.printBase64Binary(cipher.doFinal(secretKey.getBytes()));
			Claims claims = Jwts.parser().setSigningKey(encryptedKey).parseClaimsJws(token).getBody();
			userData = new HashMap<String, Claims>();
			userData.put(claims.getSubject(), claims);			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			
		}		
		return userData;
	}
	
	public static void main(String[] args) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		String strPassPhrase = Dir_ResourceBundle.getString("PASS_PHRASE");
		String secretKey = Dir_ResourceBundle.getString("SECRET_KEY");
		String issuer = Dir_ResourceBundle.getString("ISSUER");
		System.out.println("Text : " + secretKey);
		String encryptedKey = null;
		String token = null;
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("DESede");
			SecretKey key = factory.generateSecret(new DESedeKeySpec(strPassPhrase.getBytes()));
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encryptedKey = DatatypeConverter.printBase64Binary(cipher.doFinal(secretKey.getBytes()));
			System.out.println("Encrypted " + encryptedKey);
			cipher.init(Cipher.DECRYPT_MODE, key);
			String decryptedKey = new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(encryptedKey)));
			System.out.println("Text Decryted : " + decryptedKey.equals(secretKey));
			Calendar currentDate = Calendar.getInstance();
			long dateInCal = currentDate.getTimeInMillis();
			Date issuedAt = currentDate.getTime();
			Date expiry = new Date(
					dateInCal + (Integer.valueOf(Dir_ResourceBundle.getString("SESSION_EXPIRY_DURATION"))));
			token = Jwts.builder().claim("userRole", "DIA").setSubject("john.larsson").setIssuer(issuer)
					.setIssuedAt(issuedAt).setExpiration(expiry).signWith(signatureAlgorithm, encryptedKey).compact();
			System.out.println(token);
			Claims claims = Jwts.parser().setSigningKey(encryptedKey).parseClaimsJws(token).getBody();
			System.out.println("Subject: " + claims.getSubject());
			System.out.println("Issuer: " + claims.getIssuer());
			System.out.println("Expiration: " + claims.getExpiration());
			System.out.println("User Role: " + claims.get("userRole"));
			
			
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}		
	}
}