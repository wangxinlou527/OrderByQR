package com.jydp.obqr.entity;

/*
2020/4/25 16:32 星期六
作用 ：
*/public class LoginEntity {

    /**
     * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvcXJvcmRlci5qeWRwLndvcmtcL3N0YWZmXC9hdXRob3JpemF0aW9ucyIsImlhdCI6MTU4NzgwMjY0OSwiZXhwIjoxNTg3ODA2MjQ5LCJuYmYiOjE1ODc4MDI2NDksImp0aSI6IkVIRUhRbnRLOVJpWFZMeGIiLCJzdWIiOjQsInBydiI6IjdlYmI4YTJjYzFkOTViNjJjOTU5NGEyMmM5Y2VjMjJmMzhkYjVkMzEifQ.2AfUcwEhj1UEDHO0wVMqJMI2w0pMqG3KrnHzRBgs_yw
     * token_type : Bearer
     * expires_in : 3600
     */

    private String access_token;
    private String token_type;
    private int expires_in;
    private int merchant_id;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }


}
