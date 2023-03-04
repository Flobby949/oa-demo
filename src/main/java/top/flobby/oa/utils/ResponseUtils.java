package top.flobby.oa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : Flobby
 * @program : my-oa
 * @description : 统一响应封装
 * @create : 2023-02-27 15:51
 **/

@Data
public class ResponseUtils {
    private String code;
    private String message;
    private Map<String, Object> data = new LinkedHashMap<>();

    public ResponseUtils() {
        this.code = "0";
        this.message = "操作成功";
    }

    public ResponseUtils(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseUtils put(String name, Object o) {
        this.data.put(name, o);
        return this;
    }

    public String toJsonString() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json;
        try {
            json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}
