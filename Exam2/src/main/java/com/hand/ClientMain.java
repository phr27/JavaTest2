package com.hand;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

    public static void main(String[] args) {
        File dir = new File("Exam2/tmp");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (
                Socket client = new Socket("localhost", 8080);
                BufferedInputStream bis = new BufferedInputStream(client.getInputStream());
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Exam2/tmp/SampleChapter1.pdf"))
        ) {

            byte[] buf = new byte[512];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("文件读取完成，关闭连接");
    }
}
