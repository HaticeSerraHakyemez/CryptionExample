package CryptionExample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import CryptionExample.cryptor.Decryptor;
import CryptionExample.cryptor.Encryptor;


public class mainClass {

	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {

		//file cryption
		File inputFile=new File("example.docx");
		FileInputStream inputStream= new FileInputStream(inputFile);
		File outputFile=new File("exampleCopy.docx");
		FileOutputStream outputStream= new FileOutputStream(outputFile);
		
		byte[] buffer=new byte[64];
		
		int bytesRead;
		
		while((bytesRead=inputStream.read(buffer))!=(-1)) {
			outputStream.write(buffer);
		}
		
		inputStream.close();
		outputStream.close();
		
		
		
		try {
			String password = "abvcvs47y"; // needs to be at least 8 characters for DES

			FileInputStream fis = new FileInputStream("input.txt");
			FileOutputStream fos = new FileOutputStream("enc.txt");
			Encryptor.encrypt(fis, fos, password);

			FileInputStream fis2 = new FileInputStream("enc.txt");
			FileOutputStream fos2 = new FileOutputStream("dec.txt");
			Decryptor.decrypt(fis2, fos2, password);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		//string crypt
		String key="12345678";
		String str="Hello World!";
		Cipher cipher=Cipher.getInstance("DES");
		SecretKeyFactory keyFactObj=SecretKeyFactory.getInstance("DES");
		DESKeySpec desKey=new DESKeySpec(key.getBytes());
		SecretKey secretKey=keyFactObj.generateSecret(desKey);
		cipher.init(Cipher.ENCRYPT_MODE,secretKey);
		byte[] encryptedData = cipher.doFinal(str.getBytes());
		System.out.println(new String(encryptedData));
		cipher.init(Cipher.DECRYPT_MODE,secretKey);
		byte[] decryptedData = cipher.doFinal(encryptedData);
		System.out.println(new String(decryptedData));
		
	
	
	}
	
}
