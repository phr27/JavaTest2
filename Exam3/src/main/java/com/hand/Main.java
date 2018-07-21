package com.hand;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.bean.Share;
import com.hand.bean.XmlRoot;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        File dir = new File("Exam3/tmp");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        System.out.println("[INFO] 股票编码：" + args[0]);

        URL url = new URL("http://hq.sinajs.cn/list=" + args[0]);
        URLConnection connection = url.openConnection();

        System.out.println("[INFO] 开始获取数据");

        StringBuilder data = new StringBuilder();
        try (BufferedInputStream bis = new BufferedInputStream(connection.getInputStream())) {
            byte[] buf = new byte[32];
            int len;
            while ((len = bis.read(buf)) != -1) {
                data.append(new String(buf, 0, len, "GBK"));
            }
        }

        Matcher matcher = Pattern.compile("var.+=\\\"(.+?),(.+?),(.+?),(.+?),(.+?),(.+?),").matcher(data);
        if (matcher.find()) {
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
//            System.out.println(matcher.group(4));
//            System.out.println(matcher.group(5));
//            System.out.println(matcher.group(6));
            parse(new XmlRoot(new Share(
                    matcher.group(1),
                    Double.parseDouble(matcher.group(2)),
                    Double.parseDouble(matcher.group(3)),
                    Double.parseDouble(matcher.group(4)),
                    Double.parseDouble(matcher.group(5)),
                    Double.parseDouble(matcher.group(6))
            )), args[0]);
        } else {
            System.out.println("[INFO] 数据提取失败");
        }
    }

    private static void parse(XmlRoot xml, String code) {
        // 解析为 xml 线程
        new Thread(() -> {
            // todo
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Share.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("Exam3/tmp/" + code + ".xml"), "UTF-8"), true)) {
                    marshaller.marshal(xml, pw);
                    System.out.println("[INFO] xml 解析完成");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }).start();

        // 解析为 json 线程
        new Thread(() -> {
            ObjectMapper mapper = new ObjectMapper();
            try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("Exam3/tmp/" + code + ".json"), "UTF-8"), true)) {
                mapper.writeValue(pw, xml.getShare());
                System.out.println("[INFO] json 解析完成");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
