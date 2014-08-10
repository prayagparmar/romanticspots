package com.bitchinc.romantics.boot;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;

/**
 * User: prayagparmar
 * Date: 8/10/14
 * Time: 2:03 PM
 */
public class Test {

    public static void main(String[] args) throws Exception {

        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpPost httpost = new HttpPost("http://localhost:8080/romantic-spots-0.1.0/user/register");
        StringEntity params =new StringEntity("{\"username\":\"applifyed\",\"password\":\"apple\",\"email\":\"ab@ab.com\"} ");

        httpost.addHeader("content-type", "application/json");
        httpost.setEntity(params);

        HttpResponse response = httpclient.execute(httpost);

        HttpEntity entity = response.getEntity();

        System.out.println("Login form get: " + response.getStatusLine());
        if (entity != null) {
            entity.consumeContent();
        }

        System.out.println("Post logon cookies:");
        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
        if (cookies.isEmpty()) {
            System.out.println("None");
        } else {
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("- " + cookies.get(i).toString());
            }
        }

        httpclient.getConnectionManager().shutdown();
    }
}
