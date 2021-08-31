package cn.itcast.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpConfig {
    public static void main(String[] args) {
        //打开浏览器，创建httpclient对象
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //输入网址,发起get请求创建HttpGet对象
        HttpGet httpGet=new HttpGet("http://www.itcast.cn");

        //设置请求地址是：http://yun.itheima.com/search?keys=Java
        //创建URIBuilder
        //URIBuilder uriBuilder=new URIBuilder("http://yun.itheima.com/search");

        //设置参数
        //uriBuilder.setParameter("keys","Java");


        //配置请求信息
        RequestConfig config=RequestConfig.custom().setConnectTimeout(1000)
          .setConnectionRequestTimeout(500)
          .setSocketTimeout(10*1000)
          .build();
        //给http请求设置请求信息
        httpGet.setConfig(config);
        //创建HttpGet对象，设置url访问地址
       // HttpGet httpGet=new HttpGet(uriBuilder.build());
        //按回车，发起请求，返回响应
        CloseableHttpResponse response=null;
        try{
            response= httpClient.execute(httpGet);

            //解析响应，获取数据
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


    }

}
