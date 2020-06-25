package com.github.uugan.ebarimt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.uugan.ebarimt.common.Config;
import com.github.uugan.ebarimt.common.RequestType;
import com.github.uugan.ebarimt.common.Service;
import com.github.uugan.ebarimt.vat.*;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 *
 */
@Slf4j
public final class Ebarimt {

    private final Gson _gson;
    private static Ebarimt _instance;
    private static Config _svc;

    private Ebarimt() {
        this._gson = new Gson();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            _svc = mapper.readValue(new File(System.getProperty("conf", "config.yml")), Config.class);
            log.debug("config yml:" + ReflectionToStringBuilder.toString(_svc, ToStringStyle.MULTI_LINE_STYLE));
        } catch (Exception e) {
            System.out.println("Reading config file:" + e.getMessage());
            log.error("", e);
        }
    }

    public static Ebarimt get_instance() {
        return _instance;
    }

    public static Ebarimt create() {
        _instance = new Ebarimt();
        return _instance;
    }

    public String toJson(Object o) {
        return _gson.toJson(o);
    }


    /**
     * @param companyName name of company
     * @param requestType type of request
     * @return
     */
    public Bill initVAT(String companyName, RequestType requestType) throws Exception {
        String url = "";
        Service svc = _svc.getService(companyName);
        if (svc == null) {
            throw new Exception("Invalid company");
        }
        url = (requestType == RequestType.PUT) ? svc.getPut_url() : svc.getReturn_url();
        if ("".equals(url)) {
            throw new Exception("Invalid url");
        }

        return ((requestType == RequestType.PUT)) ? BillData.builder().url(url).build() : ReturnBill.builder().url(url).build();
    }

    /**
     * @param requestType type of request PUT or RETURN
     * @param url         HTTP POST URL
     * @return Bill
     * @throws Exception
     */
    public Bill initVAT(RequestType requestType, String url) throws Exception {
        return ((requestType == RequestType.PUT)) ? BillData.builder().url(url).build() : ReturnBill.builder().url(url).build();
    }

    /**
     * @param billData generated billdata
     * @return retuns Result
     * @throws Exception
     */
    public Result putVAT(Bill billData) throws Exception {
        if (billData instanceof BillData) {
            String strJSON = billData.toJsonStr();
            String retResult = HttpUtil.send_json(((BillData) billData).getUrl(), strJSON);
            if (isJSONValid(retResult)) {
                return _gson.fromJson(retResult, Result.class);
            } else {
                log.error("Invalid json result:" + retResult);
                throw new Exception("Invalid json result.");
            }
        } else {
            throw new Exception("Object is not an instance of BillData.");
        }
    }

    /**
     * @param url  server url for putting vat
     * @param json post data json string
     * @return
     * @throws Exception
     */
    public String putVAT(String url, String json) throws Exception {
        return HttpUtil.send_json(url, json);
    }

    /**
     * @param billData generated billdata
     * @return
     * @throws Exception
     */
    public String returnVAT(Bill billData) throws Exception {
        if (billData instanceof ReturnBill) {
            String strJSON = billData.toJsonStr();
            return HttpUtil.send_json(((ReturnBill) billData).get_url(), strJSON);
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
    public String returnVAT(String url, String json) throws Exception {
        return HttpUtil.send_json(url, json);
    }

    public CorpCheckRegNo checkRegNo(String regno) throws Exception {
        String retJSON = HttpUtil.send_get("http://info.ebarimt.mn/rest/merchant/info?regno=" + regno, null);
        if (isJSONValid(retJSON)) {
            return _gson.fromJson(retJSON, CorpCheckRegNo.class);
        } else {
            throw new Exception("Invalid json.");
        }
    }

    private boolean isJSONValid(String test) {
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
