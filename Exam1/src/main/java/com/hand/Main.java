package com.hand;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) throws IOException {
        File dir = new File("tmp/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // "http://192.168.11.205:18080/trainning/SampleChapter1.pdf"
        URL url = new URL(args[0]);
        URLConnection connection = url.openConnection();
        try (
                BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("tmp/SampleChapter1.pdf"))
        ) {
            byte[] bytes = new byte[512];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
