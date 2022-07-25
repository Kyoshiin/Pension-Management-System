package com.cts.authorization.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionDetails {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;

    private String message;


    public ExceptionDetails(LocalDateTime timeStamp, String message) {
        super();
        this.timeStamp = timeStamp;
        this.message = message;
    }
}
