package com.wb.wbao.server.request;

import com.h3c.common.concurrent.MamboExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;

@Service("requestMgr")
public class RequestMgrImpl implements RequestMgr {

    Logger logger = LoggerFactory.getLogger(RequestMgrImpl.class);

    private ExecutorService ioService = MamboExecutors.get().getIoBoundService();

    private Map<Integer, Set<RequestReceiver>> listeners = new ConcurrentHashMap<>();

    @Override
    public void register(int requestType, RequestReceiver requestReceiver) {

        Set<RequestReceiver> receivers = listeners.get(requestType);
        if(CollectionUtils.isEmpty(receivers)){
            receivers = new CopyOnWriteArraySet<>();
        }
        receivers.add(requestReceiver);
        listeners.put(requestType, receivers);
        logger.info("add an listener, listener's size is {}", receivers.size());
    }

    @Override
    public Long postRequest(RequestData requestData) {

        int requestType = requestData.getRequestType();

        Set<RequestReceiver> receivers = listeners.get(requestType);

        if(CollectionUtils.isEmpty(receivers)) {
            logger.info("empty listner");
            return null;
        }

        /** TODO 推送前台 */
        Long msgId = requestData.isSendWebMsg() ? 1L : null;

        try {
            receivers.forEach(task -> ioService.execute(() -> task.handleRequest(requestData)));
        } catch (Exception e) {
            logger.info("exception show {}", e);
        }

        return msgId;
    }
}
