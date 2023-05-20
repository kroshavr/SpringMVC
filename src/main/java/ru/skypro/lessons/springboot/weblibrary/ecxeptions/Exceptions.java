package ru.skypro.lessons.springboot.weblibrary.ecxeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.sql.SQLException;

public class Exceptions {
    @ExceptionHandler
    public ResponseEntity<String> handleIOException(IOException ioException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка ввода/вывода: " + ioException.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleSQLException(SQLException sqlException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка SQL: " + sqlException.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Некорректное значение id/salary (несовпадение типа данных): " + exception.getMessage());
    }
}
