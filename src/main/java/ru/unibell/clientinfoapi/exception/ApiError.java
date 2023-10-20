package ru.unibell.clientinfoapi.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private LocalDateTime time;
    private Integer code;
    private HttpStatus status;
    private String message;

}
