package cn.itcast.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.util.List;
import java.util.ArrayList;

public class HttpClientPost {
    public static void main(String[] args)throws Exception {
        //打开浏览器，创建httpclient对象
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //输入网址,发起get请求创建HttpGet对象
        HttpPost httpPost=new HttpPost("http://yun.itheima.com/search");

        //申明List集合，封装表单中的参数
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        //设置参数
        params.add(new BasicNameValuePair("keys","Java"));
        //创建表单的Entity对象，第一个参数就是封装好的表单数据，第二个参数就是编码
        UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(params,"utf8");
        //设置表单里的Entity对象到post请求里
        httpPost.setEntity(formEntity);
        //创建HttpGet对象，设置url访问地HttpGet httpGet=new HttpGet(uriBuilder.build());
        //按回车，发起请求，返回响应
        CloseableHttpResponse response=null;
        try{
            response= httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()==200){
                HttpEntity httpEntity=response.getEntity();
                String content= EntityUtils.toString(httpEntity,"utf8");
                System.out.println(content);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                response.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //解析响应，获取数据
    }
}
