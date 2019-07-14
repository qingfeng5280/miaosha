package com.miaoshaproject.error;

public class BusinessException extends Exception implements CommonError {

    private CommonError commonError ;


    //直接接受EmBusinessError的传参用于构造业务异常
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError ;
    }

    //


    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setMsg(String errMsg) {
        this.commonError.setMsg(errMsg) ;
        return this;
    }
}
