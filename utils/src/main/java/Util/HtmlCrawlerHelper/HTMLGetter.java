package Util.HtmlCrawlerHelper;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HTMLGetter {

    public static void main(String[] args) {
        new HTMLGetter().downloadHtml("http://www.bilibili.com");
    }

    /**
     * 打开并且存储一个网页
     * 下面这段代码没用到HTTPClient，所以没用到HTTPClient伪装成浏览器，是不安全的：
     * 1是网站会根据访问者的类型来屏蔽访问.2是在爬取的时候访问网站会非常频繁,网站会封掉我们的IP
     * 在java中实现这一点比较常用的是使用HttpClient的jar包来实现
     *
     *
     * 安全写法见下方函数
     * @param link
     */
    /**
     * 等价 python代码：
     * import urllib.request
     * def downloadHtml(link):
     * try:
     * data = urllib.request.urlopen(link).read()
     * text = data.decode('utf-8','ignore')
     * file = open('f:\\test2.txt','w')
     * file.write(text)
     * file.close()
     * except urllib.request.URLError as e:
     * print(e.reason)
     * downloadHtml("http://www.bilibili.com")
     *
     * @param link
     */
    public void downloadHtml(String link) {
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            URL url = new URL(link);
            URLConnection con = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            writer = new FileWriter("f://test1.txt");
            String buff = null;
            StringBuilder sb = new StringBuilder();
            while ((buff = reader.readLine()) != null) {
                writer.write(buff);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打开并且存储一个网页
     * 用到HTTPClient，安全寫法
     * @param url
     * @return
     */
    /**
     * import urllib.request
     *
     * def downloadHtml(link):
     *     try:
     *         proxy = {"http":"123.179.128.170:8080"}
     *         proxy_support = urllib.request.ProxyHandler(proxy)
     *         headers = ( "User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
     *         opener = urllib.request.build_opener(proxy_support)
     *         opener.addheaders = [headers]
     * 	opener = urllib.request.build_opener(proxy_support)
     * data = opener.open(link).read() text = data.decode('utf-8','ignore') print(text) except urllib.request.URLError as e: print(e.reason)def dowmloadHtml2(link): req = urllib.request.Request(link) req.add_header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36") data = urllib.request.urlopen(req,timeout=1).read() text = data.decode("utf-8","ignore") print(text)downloadHtml("http://blog.csdn.net/weiwei_pig/article/details/51178226")
     * @param url
     * @return
     */
    public String gethtml(String url) {
        String html = null;
//建立HttpClient类,这是HttpClient的jar包中的一个类
        CloseableHttpClient httpclient = HttpClients.createDefault();
//读取html的流
        BufferedReader reader = null;
//设置请求信息
        HttpGet getmethod = new HttpGet(url);
//响应
        HttpResponse response = null;
//设置代理IP
        HttpHost proxy = new HttpHost("124.88.67.81", 80);
//设置超时时间
        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(5000).build();
//添加请求头
        getmethod.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        getmethod.setConfig(config);
        try {
//获得响应
            response = httpclient.execute(getmethod);
//响应体
            HttpEntity entity = response.getEntity();
//建立一个读取流
            reader = new BufferedReader(new InputStreamReader(entity.getContent()));
//读取html
            String buff = null;
            StringBuilder sb = new StringBuilder();
            while ((buff = reader.readLine()) != null) {
                sb.append(buff);
            }
            html = sb.toString();
            System.out.println();
            return html;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("读取异常");
            return null;
        } finally {
            try {
                httpclient.close();
                reader.close();
            } catch (IOException e) {
                System.out.println("关闭资源失败");
            }
        }
        return html;
    }


}