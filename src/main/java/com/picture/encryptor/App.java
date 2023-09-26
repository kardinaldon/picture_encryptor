package com.picture.encryptor;

import com.picture.encryptor.service.CryptoService;
import com.picture.encryptor.service.FileService;

import javax.crypto.Cipher;
import java.io.IOException;
import java.util.Scanner;

public class App {

    private static String filePath = System.getProperty("user.dir");

    public static void main(String[] args) {
        FileService fs = new FileService();
        CryptoService cs = new CryptoService();
        try (Scanner in = new Scanner(System.in)){
            System.out.println("Enter the full file address");
            String filePath = in.nextLine();
            try {
                byte[] file = fs.getFile(filePath);
                byte[] encryptedFile = cs.makeAes(file, Cipher.ENCRYPT_MODE);
                String savedEncryptedFile = fs.saveFile(encryptedFile, filePath + "_encrypted_file.jpg");
                byte[] decryptedFile = cs.makeAes(fs.getFile(savedEncryptedFile), Cipher.DECRYPT_MODE);
                String savedDecryptedFile = fs.saveFile(decryptedFile, filePath + "_decrypted_file.jpg");
                System.out.println("file encryption/decryption completed successfully");
                System.out.println("encrypted file - " + savedEncryptedFile);
                System.out.println("decrypted file - " + savedDecryptedFile);
            } catch (IOException e) {
                System.out.println("file not found or could not be processed");
                e.getCause();
            }
        }
    }

}
