package com.github.uugan.ebarimt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.uugan.ebarimt.common.Config;
import com.github.uugan.ebarimt.common.RequestType;
import com.github.uugan.ebarimt.common.Service;
import com.github.uugan.ebarimt.bean.*;
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

    /**
     * @param companyName name of company
     * @return
     */
    public Service initVAT(String companyName) throws Exception {
        Service svc = _svc.getService(companyName);
        if (svc == null) {
            throw new Exception("Invalid company");
        }
        return svc;
    }
}
