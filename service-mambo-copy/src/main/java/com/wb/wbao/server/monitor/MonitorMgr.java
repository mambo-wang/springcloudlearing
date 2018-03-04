package com.wb.wbao.server.monitor;

import com.wb.wbao.server.request.RequestData;

import java.util.concurrent.Future;

public interface MonitorMgr {


    Future<TaskMsg> postRequest(RequestData requestData, Long msgId);

}
