package com.dionisius.finalproject.logservice.service;

import com.dionisius.finalproject.logservice.model.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaLogImpl implements KafkaLog{
    private final static String topic = "logService";
    private final static String groupId = "bniFinalProject";
    private final LogService logService;

    @KafkaListener(topics = topic, groupId = groupId)
    public void writeLog(String logMessage) {
        Log log = Log.builder().log_data(logMessage).build();
        logService.create(log);
    }
}
