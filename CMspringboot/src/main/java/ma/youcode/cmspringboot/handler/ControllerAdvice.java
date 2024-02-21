package ma.youcode.cmspringboot.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
@ExceptionHandler(Throwable.class)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public ResponseEntity<Map<String, String>> handleGenericException(Throwable ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "Internal Server Error");
    error.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
}
@ExceptionHandler(IllegalStateException.class)
public ResponseEntity<Map<String, String>> handelIllegalState(IllegalStateException il){
    Map<String, String> error = new HashMap<>();
    error.put("error","Internal Server Error");
    error.put("message",il.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
}
}
