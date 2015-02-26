package com.sunonerim.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;






public class CipherUtil {
	static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0xFF, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	
	public static byte[] AES_Encode(byte[] ivBytes, byte[] keyBytes, byte[] textBytes)
	throws java.io.UnsupportedEncodingException,
	NoSuchAlgorithmException,
	NoSuchPaddingException,
	InvalidKeyException,
	InvalidAlgorithmParameterException,
	IllegalBlockSizeException,
	BadPaddingException {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = null;
		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
		return cipher.doFinal(textBytes);
	}

	public static String  encode(String src, String key)   throws java.io.UnsupportedEncodingException,
																						NoSuchAlgorithmException,NoSuchPaddingException,
																						InvalidKeyException,	InvalidAlgorithmParameterException,
																						IllegalBlockSizeException,	BadPaddingException {
		
		 byte[] keyBytes =  key.getBytes("UTF-8");
		 byte[] textBytes = src.getBytes("UTF-8");
		 
		 byte[] keyBytes2= new byte[16];
		if ( keyBytes.length < 16 ){
			System.out.println("small ");
			for ( int i=0; i<keyBytes.length; i++) keyBytes2[i] = keyBytes[i];
			for ( int i=keyBytes.length; i <16; i++) keyBytes2[i] = (byte)0;
			keyBytes = keyBytes2;				
		} else if (keyBytes.length > 16  ){
			System.out.println("key is large ");
			for ( int i=0; i<16; i++) keyBytes2[i] = keyBytes[i];
			keyBytes = keyBytes2;
		} 
			
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = null;
		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
		return Base64.encodeBase64String(cipher.doFinal(textBytes));
	}
	
	public static byte[] AES_Decode(byte[] ivBytes, byte[] keyBytes, byte[] textBytes)
																				throws java.io.UnsupportedEncodingException,NoSuchAlgorithmException,
																				NoSuchPaddingException, 	InvalidKeyException, 	InvalidAlgorithmParameterException,
																				IllegalBlockSizeException,	BadPaddingException {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
		return cipher.doFinal(textBytes);
	}
	
	public static String decode(String enc_text, String key )
																				throws java.io.UnsupportedEncodingException,NoSuchAlgorithmException,
																				NoSuchPaddingException, 	InvalidKeyException, 	InvalidAlgorithmParameterException,
																				IllegalBlockSizeException,	BadPaddingException {
		byte[] keyBytes =  key.getBytes("UTF-8");
		byte[] textBytes =  Base64.decodeBase64(enc_text); // enc_text.getBytes("UTF-8");
	
		 byte[] keyBytes2= new byte[16];
		if ( keyBytes.length < 16 ){
			System.out.println("small ");
			for ( int i=0; i<keyBytes.length; i++) keyBytes2[i] = keyBytes[i];
			for ( int i=keyBytes.length; i <16; i++) keyBytes2[i] = (byte)0;
			keyBytes = keyBytes2;				
		} else if (keyBytes.length > 16  ){
			System.out.println("key is large ");
			for ( int i=0; i<16; i++) keyBytes2[i] = keyBytes[i];
			keyBytes = keyBytes2;
		}  
		
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
		return new String(cipher.doFinal(textBytes), "UTF-8");
	}
	
}
