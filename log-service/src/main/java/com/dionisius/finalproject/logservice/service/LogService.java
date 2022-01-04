package com.dionisius.finalproject.logservice.service;

import com.dionisius.finalproject.logservice.model.Log;

import java.util.List;

public interface LogService {
    Log create(Log log);
    List<Log> listLog();
}
