package pers.yueer.api.common.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseData {
    private Integer code;
    private String message;
    public Map<String,Object> data = new HashMap<>();

    public ResponseData data(String key, Object value) {
        this.data.put(key, value);

        return this;
    }

    public ResponseData success(String msg) {

        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.setMessage(msg);

        return responseData;
    }

    public ResponseData error(String msg) {

        ResponseData responseData = new ResponseData();
        responseData.setCode(201);
        responseData.setMessage(msg);

        return responseData;
    }
}
