package cinema.Exceptions;

public class TicketPurchasedException extends RuntimeException{
    public TicketPurchasedException(String message){
        super(message);
    }
}
