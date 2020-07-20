package com.gag.common.entity;

import java.util.HashMap;

/**
 * Description: 统一返回体
 * User: scot
 * Date: 2020-07-02
 * Time: 9:57
 */

public class GaGResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public GaGResponse message(String message){
        this.put("message",message);
        return this;
    }

    public GaGResponse data(Object data){
        this.put("data",data);
        return this;
    }

    @Override
    public Object put(String key, Object value) {
         super.put(key, value);
         return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
