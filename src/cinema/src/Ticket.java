package cinema.src;

import java.util.UUID;

public class Ticket {
    private Seat ticket;
    private String token;
    public Ticket(Seat ticket){
        this.ticket=ticket;
        token = UUID.randomUUID().toString();
    }
    public Ticket(){}

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
