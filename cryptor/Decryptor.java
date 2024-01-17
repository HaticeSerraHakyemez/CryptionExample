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
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class Decryptor {

	public static void decrypt(FileInputStream input,FileOutputStream output,String key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, IOException {
	
		Cipher cipher=Cipher.getInstance("DES");
		SecretKeyFactory keyFactObj=SecretKeyFactory.getInstance("DES");
		DESKeySpec desKey=new DESKeySpec(key.getBytes());
		SecretKey secretKey = keyFactObj.generateSecret(desKey);
		cipher.init(Cipher.DECRYPT_MODE,secretKey);
		CipherOutputStream cos = new CipherOutputStream(output, cipher);
		byte[] buffer = new byte[64];
		int numBytes;
		while ((numBytes = input.read(buffer)) != -1) {
			cos.write(buffer, 0, numBytes);
		}
		cos.close();
		input.close();
	}

}
