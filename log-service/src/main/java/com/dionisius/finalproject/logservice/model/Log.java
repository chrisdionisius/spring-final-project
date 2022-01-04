package com.dionisius.finalproject.logservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("logs")
public class Log {
    @Id
    private String id;
    private String log_data;
}
