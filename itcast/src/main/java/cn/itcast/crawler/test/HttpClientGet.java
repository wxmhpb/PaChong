package cn.itcast.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientGet {
    public static void main(String[] args)throws Exception {
        //打开浏览器，创建httpclient对象
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //输入网址,发起get请求创建HttpGet对象
        //HttpGet httpGet=new HttpGet("http://www.itcast.cn");

        //设置请求地址是：http://yun.itheima.com/search?keys=Java
        //创建URIBuilder
        URIBuilder uriBuilder=new URIBuilder("http://yun.itheima.com/search");

        //设置参数
        uriBuilder.setParameter("keys","Java");

        //创建HttpGet对象，设置url访问地址
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        //按回车，发起请求，返回响应
        CloseableHttpResponse response=null;
        try{
            response= httpClient.execute(httpGet);
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
