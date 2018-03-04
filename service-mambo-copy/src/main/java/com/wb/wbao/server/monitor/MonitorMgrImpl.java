package com.wb.wbao.server.monitor;

import com.h3c.common.concurrent.MamboExecutors;
import com.wb.wbao.server.request.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service("monitorMgr")
public class MonitorMgrImpl implements MonitorMgr {


    private ExecutorService cpuBoundService = MamboExecutors.get().getCpuBoundService();

    private Logger logger = LoggerFactory.getLogger(MonitorMgrImpl.class);

    @Override
    public Future<TaskMsg> postRequest(RequestData requestData, Long msgId) {
        return cpuBoundService.submit(new MonitorProgressTask(requestData, msgId));
    }

    private class MonitorProgressTask implements Callable<TaskMsg> {

        private RequestData requestData;

        private Long msgId;

        public MonitorProgressTask(RequestData requestData, Long msgId) {
            this.requestData = requestData;
            this.msgId = msgId;
        }

        @Override
        public TaskMsg call() throws Exception {

            try {
                TaskMsg result = new TaskMsg();
                result.setStart(new Date());
                int progress = 0;
                logger.info("call msgId is {}", msgId);
                while (true) {

                    progress = progress + 10;
                    result.setProgress(progress);

                    if(result.getProgress() == 100){
                        result.setCompleted(true);
                        result.setResult(TaskMsg.SUCCESS);
                    }

                    if(result.isCompleted()){
                        result.setComplete(new Date());
                        return result;
                    }

                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
