package com.fjy.personalschedule.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpPostUtils {


    public static String requestByPost (String url, Map<String,Object> headerMap, Map<String,Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 设置连接主机服务超时时间
                .setConnectionRequestTimeout(5000)  // 设置链接请求超时时间
                .setSocketTimeout(5000) // 设置读取数据链接超时时间
                .build();
        // 为httpPost设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        if (headerMap != null && headerMap.size() > 0) {
            Set<Map.Entry<String, Object>> entries = headerMap.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                httpPost.addHeader(next.getKey(),next.getValue().toString());
            }
        }
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<>();
            Set<Map.Entry<String, Object>> entries = paramMap.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                nvps.add(new BasicNameValuePair(next.getKey(),next.getValue().toString()));
            }
            // 为httpPost设置请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient执行post
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String postWithJson (String url, Map<String,Object> headerMap, String jsonContent) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000) // 设置连接主机服务超时时间
                .setConnectionRequestTimeout(10000)  // 设置链接请求超时时间
                .setSocketTimeout(10000) // 设置读取数据链接超时时间
                .build();
        // 为httpPost设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.setHeader("Content-Type", "application/json");
        if (headerMap != null && headerMap.size() > 0) {
            Set<Map.Entry<String, Object>> entries = headerMap.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                httpPost.addHeader(next.getKey(),next.getValue().toString());
            }
        }
        // 为httpPost设置请求参数
        try {
            httpPost.setEntity(new StringEntity(jsonContent,"utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // httpClient执行post
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

    }
}
