package com.jydp.obqr.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jydp.obqr.net.IResponse;

import org.json.JSONObject;

/*
2020/4/25 16:32 星期六
作用 ：
*/public class ErrorEntity implements IResponse {

    /**
     * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvcXJvcmRlci5qeWRwLndvcmtcL3N0YWZmXC9hdXRob3JpemF0aW9ucyIsImlhdCI6MTU4NzgwMjY0OSwiZXhwIjoxNTg3ODA2MjQ5LCJuYmYiOjE1ODc4MDI2NDksImp0aSI6IkVIRUhRbnRLOVJpWFZMeGIiLCJzdWIiOjQsInBydiI6IjdlYmI4YTJjYzFkOTViNjJjOTU5NGEyMmM5Y2VjMjJmMzhkYjVkMzEifQ.2AfUcwEhj1UEDHO0wVMqJMI2w0pMqG3KrnHzRBgs_yw
     * token_type : Bearer
     * expires_in : 3600
     */

    private String message;
    private int status_code;
    private JsonObject errors;

    public JsonObject getErrors() {
        return errors;
    }

    public void setErrors(JsonObject errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
}
