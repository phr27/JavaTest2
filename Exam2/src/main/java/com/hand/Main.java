package com.hand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端入口
 */
public class Main {

    public static void main(String[] args) {

        try (
                ServerSocket serverSocket = new ServerSocket(8080);
                Socket socket = serverSocket.accept();
                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("Exam1/tmp/SampleChapter1.pdf"))
        ) {
            System.out.println("连接已建立");
            byte[] buf = new byte[512];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("文件发送完成，关闭连接");
    }
}
