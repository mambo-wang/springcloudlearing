package com.wb.wbao.unitest;

import com.wb.wbao.server.monitor.MonitorMgr;
import com.wb.wbao.server.monitor.TaskMsg;
import com.wb.wbao.server.request.RequestData;
import com.wb.wbao.server.request.RequestMgr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestMgrTest {

    @Resource
    private RequestMgr requestMgr;

    @Resource
    private MonitorMgr monitorMgr;

    @Test
    public void testCall() {

        Future<TaskMsg> future = monitorMgr.postRequest(null, null);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRequestAndMonitor() {

        RequestData requestData = new RequestData(RequestData.ASYNC_TEST_ONE, "good", null, 1L);

        requestMgr.postRequest(requestData);

        try {
            TimeUnit.SECONDS.sleep(30L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
