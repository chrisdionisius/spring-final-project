package com.dionisius.finalproject.logservice.service;

import com.dionisius.finalproject.logservice.model.Log;
import com.dionisius.finalproject.logservice.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService{

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Log create(Log log) {
        return logRepository.save(log);
    }

    @Override
    public List<Log> listLog() {
        return logRepository.findAll();
    }
}
