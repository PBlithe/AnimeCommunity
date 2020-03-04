package com.anime.endsystem.message;

import java.io.Serializable;

public class ResultBean implements Serializable {
    private Integer code;// 1000:success # -1:error
    private String  message;
    private Object  data;

    static ResultBean resultBean=new ResultBean();


    public ResultBean success(){
        ResultBean rb=resultBean;
        rb.setData(null);
        rb.setMessage("success");
        rb.setCode(1000);
        return  rb;
    }

    public ResultBean success(Object obj){
        ResultBean rb=resultBean;
        rb.setData(obj);
        rb.setMessage("success");
        rb.setCode(1000);
        return  rb;
    }

    public ResultBean success(Object obj, String message){
        ResultBean rb=resultBean;
        rb.setData(obj);
        rb.setMessage(message);
        rb.setCode(1000);
        return  rb;
    }


    public ResultBean error(){
        ResultBean rb=resultBean;
        rb.setData(null);
        rb.setMessage("error");
        rb.setCode(-1);
        return  rb;
    }

    public ResultBean error(Object obj){
        ResultBean rb=resultBean;
        rb.setData(obj);
        rb.setMessage("success");
        rb.setCode(-1);
        return  rb;
    }

    public ResultBean error(Object obj, String message){
        ResultBean rb=resultBean;
        rb.setData(obj);
        rb.setMessage(message);
        rb.setCode(-1);
        return  rb;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
