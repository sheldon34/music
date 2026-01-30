//package com.example.music.Controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//import org.springframework.web.multipart.MaxUploadSizeExceededException;
//import org.springframework.web.multipart.MultipartException;
//import org.springframework.web.multipart.support.MissingServletRequestPartException;
//
//import java.time.format.DateTimeParseException;
//import java.util.HashMap;
//import java.util.Map;
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
//        Map<String, Object> body = new HashMap<>();
//        String name = ex.getName();
//        String type = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown";
//        String msg = "Failed to convert parameter '" + name + "' to type " + type + ".";
//        // give friendly hint for LocalDate parsing
//        if (ex.getRequiredType() != null && ex.getRequiredType().getSimpleName().equals("LocalDate")) {
//            msg += " Expected date format like yyyy-MM-dd (or parse on server from other formats).";
//        }
//        body.put("status", 400);
//        body.put("error", "Bad Request");
//        body.put("message", msg);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
//    }
//
//    @ExceptionHandler({MaxUploadSizeExceededException.class, MultipartException.class})
//    public ResponseEntity<Map<String, Object>> handleMultipartErrors(Exception ex) {
//        Map<String, Object> body = new HashMap<>();
//        String message = "Multipart error: " + ex.getMessage();
//        body.put("status", 400);
//        body.put("error", "Bad Request");
//        body.put("message", message);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
//    }
//    @ExceptionHandler({MissingServletRequestPartException.class, DateTimeParseException.class})
//    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception ex) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("status", 400);
//        body.put("error", "Bad Request");
//        body.put("message", ex.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> handleAll(Exception ex) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("status", 500);
//        body.put("error", "Internal Server Error");
//        body.put("message", ex.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
//    }
//}
