package com.chen.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * http请求
 */
public class HttpUtil {

    private static final String token_sign = "token";

    /**
     * 以get方式调用第三方接口
     * @param url 接口地址
     * @param token token
     * @return 接口返回的数据
     */
    public static String doGet(String url, String token) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        //自定义header头，用于token验证使用
        httpGet.addHeader(token_sign, token);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");

        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            LogUtil.printException(e);
        }
        return null;
    }

    /**
     * 以post方式调用第三方接口
     * @param url 接口地址
     * @param paramStr 请求参数
     * @param token token
     * @return 接口返回的数据
     */
    public static String doPost(String url, String paramStr, String token) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);

        //自定义header头，用于token验证使用
        httpPost.addHeader(token_sign, token);
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");

        try {
            StringEntity se = new StringEntity(paramStr, "UTF-8");
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");  //发送json数据需要设置contentType
            httpPost.setEntity(se);  //设置请求参数

            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            LogUtil.printException(e);
        } finally {
            if (httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    LogUtil.printException(e);
                }
            }
        }
        return null;
    }

}
