package com.picture.encryptor.service;

import java.io.*;

public class FileService {

    public byte[] getFile(String fileName) throws IOException {
        File file = new File(fileName);
        try(InputStream fileInputStream = new FileInputStream(file)) {
            byte[] content = new byte[fileInputStream.available()];
            fileInputStream.read(content);
            return content;
        }
    }

    public String saveFile(byte[] bytes, String fileName) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(fileName)){
            fos.write(bytes);
            return fileName;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return "";
        }
    }
}
