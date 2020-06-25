package com.github.uugan.ebarimt;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpUtil {

    public static String send_get(String url, Map<String, String> headers) throws Exception {
        try {
            URLConnection urlcon = new URL(url).openConnection();
            HttpURLConnection con;
            if (urlcon instanceof HttpsURLConnection) {
                con = (HttpsURLConnection) urlcon;
            } else {
                con = (HttpURLConnection) urlcon;
            }
            con.setDoInput(true);
            con.setDoOutput(false);
            con.setRequestMethod("GET");

            if (headers != null) {
                for (String key : headers.keySet()) {
                    con.setRequestProperty(key, headers.get(key));
                }
            }

            con.connect();
            log.info("Resp Code:" + con.getResponseCode());
            log.info("Resp Message:" + con.getResponseMessage());

            InputStream ins;
            if (con.getResponseCode() != 200) {
                ins = con.getErrorStream();
            } else {
                ins = con.getInputStream();
            }

            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);

            String inputLine;
            String retval = "";

            while ((inputLine = in.readLine()) != null) {
                retval += inputLine + "\r\n";
            }

            in.close();
            log.info("Response String:" + retval);
            return retval;
        } catch (Exception e) {
            log.error("", e);
            throw e;
        }
    }

    public static String[] http_post(String url, byte[] data, Map<String, String> headers, int ctime, int rtime) throws Exception {
        try {
            String rcode;
            log.debug("connecting to: " + url);
            HttpURLConnection con;
            if (url.startsWith("https")) {
                con = (HttpsURLConnection) new URL(url).openConnection();
            } else {
                con = (HttpURLConnection) new URL(url).openConnection();
            }
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setConnectTimeout(ctime);
            con.setReadTimeout(rtime);
            if (headers != null && !headers.isEmpty()) {
                for (String k : headers.keySet()) {
                    con.setRequestProperty(k, headers.get(k));
                }
            } else {
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }
            con.connect();
            OutputStream out = con.getOutputStream();
            out.write(data);
            out.flush();
            out.close();

            rcode = con.getResponseCode() + ":" + con.getResponseMessage();

            InputStream ins;
            if (con.getResponseCode() != 200) {
                ins = con.getErrorStream();
            } else {
                ins = con.getInputStream();
            }

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = ins.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String retval = result.toString("UTF-8");
            return new String[]{rcode, retval};
        } catch (Exception e) {
            log.error("", e);
            throw e;
        }
    }

    public static String send_json(String url, String json) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        log.debug("send post url: " + url + " data:" + json);
        String[] ret = http_post(url, json.getBytes("UTF-8"), headers, 10000, 20000);
        log.debug("response data: " + ret[1]);
        return ret[1];
    }


}
