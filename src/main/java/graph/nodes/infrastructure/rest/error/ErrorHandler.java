package graph.nodes.infrastructure.rest.error;

import graph.nodes.application.exception.InvalidGraphException;
import graph.nodes.application.exception.NodeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("An error occurred", ex.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(NodeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NodeNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("NODE_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidGraphException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(InvalidGraphException ex) {
        ErrorResponse errorResponse = new ErrorResponse("INVALID_GRAPH", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public static class ErrorResponse {
        private String code;
        private String message;

        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
