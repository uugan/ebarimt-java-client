package com.github.uugan.ebarimt;

import com.github.uugan.ebarimt.bean.*;
import com.github.uugan.ebarimt.common.RequestType;
import com.github.uugan.ebarimt.common.Service;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Slf4j
public class VatUtil {
    private static final Gson _gson = new Gson();

    public static <T extends BillResponse> T putVat(Service svc, BillRequest request, Class<T> returnClass, int timeout) throws Exception {
        if (request instanceof BillRequestData) {
            String strJSON = request.toJsonStr();
            String retResult = HttpUtil.send_json(svc.getPut_url(), strJSON, timeout);
            if (isJSONValid(retResult)) {
                return _gson.fromJson(retResult, returnClass);
            } else {
                log.error("Invalid json result:" + retResult);
                throw new Exception("Invalid json result.");
            }
        } else {
            throw new Exception("Object is not an instance of BillData.");
        }
    }

    public static <T extends BillResponse> T returnVat(Service svc, ReturnRequest request, Class<T> returnClass, int timeout) throws Exception {
        if (request instanceof ReturnRequestData) {
            String strJSON = request.toJsonStr();
            String retResult = HttpUtil.send_json(svc.getReturn_url(), strJSON, timeout);
            if (isJSONValid(retResult)) {
                return _gson.fromJson(retResult, returnClass);
            } else {
                log.error("Invalid json result:" + retResult);
                throw new Exception("Invalid json result.");
            }
        } else {
            throw new Exception("Object is not an instance of BillData.");
        }
    }

    /**
     * @param url  return URL
     * @param json
     * @return
     * @throws Exception
     */
    public String returnVAT(String url, String json, int timeout) throws Exception {
        return HttpUtil.send_json(url, json, timeout);
    }

    /**
     * @param url  server url for putting vat
     * @param json post data json string
     * @return
     * @throws Exception
     */
    public String putVAT(String url, String json, int timeout) throws Exception {
        return HttpUtil.send_json(url, json, timeout);
    }

    public static CorpCheckRegNo checkRegNo(String regno) throws Exception {
        String retJSON = HttpUtil.send_get("http://info.ebarimt.mn/rest/merchant/info?regno=" + regno, null);
        if (isJSONValid(retJSON)) {
            return _gson.fromJson(retJSON, CorpCheckRegNo.class);
        } else {
            throw new Exception("Invalid json.");
        }
    }

    private static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
