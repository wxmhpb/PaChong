package cn.itcast.crawler.test;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientPool {
    public static void main(String[] args) {
        //创建连接池管理器
        PoolingHttpClientConnectionManager cm=new PoolingHttpClientConnectionManager();
        //使用连接池管理器发起请求
        doGet(cm);
    }
    public static void doGet(PoolingHttpClientConnectionManager cm){}

}
