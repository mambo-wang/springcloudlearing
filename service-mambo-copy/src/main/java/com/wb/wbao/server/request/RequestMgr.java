package com.wb.wbao.server.request;

public interface RequestMgr {

    void register(int requestType, RequestReceiver requestReceiver);

    Long postRequest(RequestData requestData);

}
