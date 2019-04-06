package org.sid.util.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
@Setter
@Getter
@AllArgsConstructor
public class ApiError {
	 
    private HttpStatus status;
    private String message;
    private Map<String, String> errors;
  
}
