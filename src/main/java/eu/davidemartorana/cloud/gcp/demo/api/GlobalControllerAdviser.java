package eu.davidemartorana.cloud.gcp.demo.api;

import eu.davidemartorana.cloud.gcp.demo.exceptions.NotFoundException;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestControllerAdvice
public class GlobalControllerAdviser extends ResponseEntityExceptionHandler {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    @Data
    @Builder
    public static class ErrorMessage {
        private String timestamp;
        private int status;
        private String error;
        private String message;
    }

    private ErrorMessage.ErrorMessageBuilder createErrorMessage(final Exception e, final HttpStatus status) {
        return ErrorMessage.builder()
                .timestamp(DATE_FORMAT.format(new Date()))
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorMessage handle(final IllegalArgumentException e) {
        return createErrorMessage(e, HttpStatus.BAD_REQUEST)
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage handle(final NotFoundException e) {
        return createErrorMessage(e, HttpStatus.NOT_FOUND)
                .build();
    }


}
