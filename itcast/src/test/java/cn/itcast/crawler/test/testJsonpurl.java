package cn.itcast.crawler.test;

import org.apache.commons.io.FileUtils;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.junit.Test;


import javax.print.Doc;
import java.io.File;
import java.net.URL;

public class testJsonpurl {

    @Test
    public void testUrl() throws Exception{
        //解析url地址，第一个参数是访问的URL，第二个参数是访问的超时时间
        Document doc= Jsoup.parse(new URL("http://www.itcast.cn"),1000);

        //使用标签选择器，获取title标签中的内容
        String title=doc.getElementsByTag("title").first().text();

        //打印
        System.out.println(title);
    }

    @Test
    public void testString()throws Exception{
        //使用工具类读取文件，解析字符串
        String content= FileUtils.readFileToString(new File("D:\\code\\bootdo\\src\\main\\resources\\templates\\login.html"));
        Document doc=Jsoup.parse(content);
        //使用标签选择器，获取title标签中的内容
        String title=doc.getElementsByTag("title").first().text();

        //打印
        System.out.println(title);
    }

    @Test
    public void testFile()throws Exception{
        //解析文件
        Document doc=Jsoup.parse(String.valueOf(new File("D:\\code\\bootdo\\src\\main\\resources\\templates\\login.html")));
        //使用标签选择器，获取title标签中的内容
        String title=doc.getElementsByTag("title").first().text();

        //打印
        System.out.println(title);
    }


}
