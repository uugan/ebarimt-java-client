package mn.ebarimt.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String[] http_post(String url, byte[] data, Map<String, String> headers, int ctime, int rtime) throws Exception {
        try {
            String rcode;
            logger.debug("connecting to: " + url);
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
                    //logger.debug("set header:" + k + " - " + headers.get(k));
                    con.setRequestProperty(k, headers.get(k));
                }
            } else {
                // con.setRequestProperty("SOAPAction", "");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }
            con.connect();
            OutputStream out = con.getOutputStream();
            out.write(data);
            //meter.update(data.length);
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
            String retval =  result.toString("UTF-8");

            //logger.debug("Response String:" + retval);
            return new String[]{rcode, retval};
        } catch (Exception e) {
            //logger.debug(e.getMessage());
            throw e;
        } finally {
            //meter.stop();
        }
    }

    public static String send_json(String url, String json) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        logger.debug("send post url: " + url);
        logger.debug("send post data: " + json);
        String[] ret = http_post(url, json.getBytes("UTF-8"), headers, 10000, 20000);
        logger.debug("response data: " + ret[1]);
        return ret[1];
    }


}
