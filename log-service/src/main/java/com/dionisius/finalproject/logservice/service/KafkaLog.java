package com.dionisius.finalproject.logservice.service;

public interface KafkaLog {
    void writeLog(String logMessage);
}
