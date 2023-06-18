package cinema.src;

import cinema.Exceptions.SeatOutOfBoundsException;
import cinema.Exceptions.TicketPurchasedException;
import cinema.Exceptions.TokenNotFoundException;
import cinema.Exceptions.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TicketPurchasedException.class)
    public ResponseEntity<CustomResponse> handleFlightNotFound(
            TicketPurchasedException e) {

        CustomResponse body = new CustomResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatOutOfBoundsException.class)
    public ResponseEntity<CustomResponse> handleSeatOutOfBounds(
            SeatOutOfBoundsException e) {

        CustomResponse body = new CustomResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<CustomResponse> handleTokenNotFound(
            TokenNotFoundException e) {

        CustomResponse body = new CustomResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<CustomResponse> handleWrongPassword(
            WrongPasswordException e) {

        CustomResponse body = new CustomResponse(
                e.getMessage(),
                401);

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

}


class CustomResponse{
    int statusCode;
    String error;
    public CustomResponse(){}
    public CustomResponse(String message, int statusCode){
        this.error=message;
        this.statusCode=statusCode;
    }

    public String getError() {
        return error;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
