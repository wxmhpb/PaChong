package cn.itcast.jd.utils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;


public class HttpUtils {
    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        this.cm.setMaxTotal(100);

        //每个主机的最大连接数
        this.cm.setDefaultMaxPerRoute(10);

    }

    //根据请求地址，下载页面数据
    public String doGetHtml(String url) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                if(response.getEntity()!=null){
                    String content=EntityUtils.toString(response.getEntity(),"utf8");
                    return content;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(response!=null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return "";
    }
    //下载图片，返回图片名称
    public String doGetImages(String url){
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                if(response.getEntity()!=null){
                  //下载图片
                  //获取图片后缀
                  String extNmae=url.substring(url.lastIndexOf("."));

                  //创建图片名，重命名图片
                    String pickName= UUID.randomUUID().toString()+extNmae;

                    //下载图片
                    //声明OutPutStream
                    OutputStream outputStream=new FileOutputStream(new File("C:\\Users\\wuxuemei\\Desktop\\images")+pickName);
                    response.getEntity().writeTo(outputStream);
                    return pickName;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(response!=null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return "";
    }

    //设置请求信息
    private RequestConfig getConfig(){
        RequestConfig config=RequestConfig.custom().setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10*1000)
                .build();
        return config;
    }
}
