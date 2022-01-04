package com.dionisius.finalproject.logservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("logs")
public class Log {
    @Id
    private String id;
    private String log_data;
}
