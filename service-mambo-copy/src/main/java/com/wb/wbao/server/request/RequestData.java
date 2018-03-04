package com.wb.wbao.server.request;


import com.h3c.common.model.UserDTO;

public class RequestData<T> {

    public static final int ASYNC_TEST_ONE = 1;
    public static final int ASYNC_TEST_TWO = 2;

    /** 请求类型 */
    private int requestType;

    /** 请求数据 */
    private T data;

    /** 操作用户 */
    private UserDTO user;

    /** 推送前台任务台进度使用 */
    private long massageId;

    /** 推送前台任务台 */
    private boolean sendWebMsg;

    public RequestData() {
    }

    public RequestData(int requestType, T data, UserDTO user, long massageId) {
        this.requestType = requestType;
        this.data = data;
        this.user = user;
        this.massageId = massageId;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public long getMassageId() {
        return massageId;
    }

    public void setMassageId(long massageId) {
        this.massageId = massageId;
    }


    public boolean isSendWebMsg() {
        return sendWebMsg;
    }

    public void setSendWebMsg(boolean sendWebMsg) {
        this.sendWebMsg = sendWebMsg;
    }
}
