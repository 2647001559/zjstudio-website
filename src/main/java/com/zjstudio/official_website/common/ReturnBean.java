package com.zjstudio.official_website.common;

import com.alibaba.fastjson.JSON;
import com.zjstudio.official_website.tool.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * TODO 返回数据封装类
 * </p>
 *
 * @author 添柴灬少年
 * @version 1.0
 * @date 2020/2/14 18:14
 **/
@Data
public class ReturnBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String status;

    /**
     * 错误信息
     */
    private String erroMsg;

    /**
     * 响应数据
     */
    private T resData;

    /**
     * 自定义返回JSON格式信息
     * @param statusEnum
     */
    public String returnJson(StatusEnum statusEnum,T resData,String erroMsg){
        this.setStatusEnum(statusEnum);
        this.resData = resData;
        this.erroMsg = erroMsg;
        return this.toJsonString();
    }

    /**
     * 自定义返回JSON格式信息
     * @param statusEnum
     */
    public Object returnBean(StatusEnum statusEnum,T resData,String erroMsg){
        this.setStatusEnum(statusEnum);
        this.resData = resData;
        this.erroMsg = erroMsg;
        return this;
    }

    /**
     * 返回异常信息
     * @param excetion
     */
    public String excetion(Exception excetion){
        return excetion(excetion,excetion.getMessage());
    }

    /**
     *返回异常信息
     * @param excetion
     * @param erroMsg
     * @return
     */
    public String excetion(Exception excetion,String erroMsg){
        this.setStatusEnum(StatusEnum.EXCEPTION);
        this.erroMsg = erroMsg;
        this.resData = null;
        return this.toJsonString();
    }

    /**
     * 返回成功信息
     * @param resData
     * @return
     */
    public String success(T resData){
        this.setStatusEnum(StatusEnum.SUCCESS);
        this.erroMsg = "";
        this.resData = resData;
        return this.toJsonString();
    }

    /**
     * 返回错误信息
     * @param erroMsg
     * @return
     */
    public String error(String erroMsg){
        this.setStatusEnum(StatusEnum.ERROR);
        this.erroMsg = erroMsg;
        this.resData = null;
        return this.toJsonString();
    }

    /**
     * 返回失败信息
     * @param erroMsg
     * @return
     */
    public String fail(String erroMsg){
        this.setStatusEnum(StatusEnum.FAIL);
        this.erroMsg = erroMsg;
        this.resData = null;
        return this.toJsonString();
    }

    /**
     * 作为JSOn格式返回
     * @return
     */
    private String toJsonString(){
        return JSON.toJSONString(this);
    }

    /**
     * 设置枚举信息
     * @param statusEnum
     */
    private void setStatusEnum(StatusEnum statusEnum){
        this.code = statusEnum.code();
        this.status = statusEnum.status();
    }
}
