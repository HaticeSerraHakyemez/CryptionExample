package CryptionExample.cryptor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;


import javax.crypto.SecretKeyFactory;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;


public class Encryptor {
	
	
	public static void encrypt(FileInputStream input,FileOutputStream str,String key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, IOException {
		Cipher cipher=Cipher.getInstance("DES");
		SecretKeyFactory keyFactObj=SecretKeyFactory.getInstance("DES");
		DESKeySpec desKey=new DESKeySpec(key.getBytes());
		SecretKey secretKey=keyFactObj.generateSecret(desKey);
		cipher.init(Cipher.ENCRYPT_MODE,secretKey);
		CipherInputStream cis = new CipherInputStream(input, cipher);
		//byte[] encryptedData = cipher.doFinal(data.getBytes());
		byte[] buffer = new byte[64];
		int numBytes;
		while ((numBytes = cis.read(buffer)) != -1) {
			str.write(buffer, 0, numBytes);
		}
		
		cis.close();
		input.close();
	}
	
	
}
