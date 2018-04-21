package com.kaishengit.tms.com.kaishengit.tms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/*
 *  Ajax 请求需要还回的对象
 * @date 2018/4/15
 * @param
 * @return
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBean {

    private String status;
    private String message;
    private Object date;

    public ResponseBean(){

    }

    public static  ResponseBean error(String message){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("error");
        responseBean.setMessage(message);
        return responseBean;
    }

    public static ResponseBean success(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("success");
        return responseBean;
    }

    public static ResponseBean success(Object date){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setStatus("success");
        responseBean.setDate(date);
        return responseBean;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
