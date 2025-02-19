package org.project.stockservice.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExceptionDetails {
    private String message;
    private Date date;
    private String description;
}
