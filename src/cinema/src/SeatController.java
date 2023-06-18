package cinema.src;
import cinema.Exceptions.SeatOutOfBoundsException;
import cinema.Exceptions.TicketPurchasedException;
import cinema.Exceptions.TokenNotFoundException;
import cinema.Exceptions.WrongPasswordException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SeatController {
    @Autowired
    private SeatService service;
    private int profit = 0;

    private ArrayList<Ticket> purchased_tickets = new ArrayList<Ticket>();

    @PostMapping("/stats")
    public ResponseEntity getStats(@RequestParam(required = false) String password){
        if(password == null || password.isEmpty() || !password.equals("super_secret")){
            throw new WrongPasswordException("The password is wrong!");
        }
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("current_income", profit);
        body.put("number_of_available_seats", service.getSeats().size());
        body.put("number_of_purchased_tickets", 81-service.getSeats().size());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/seats")
    public String getSeats() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(service);
    }

    @PostMapping("/purchase")
    public ResponseEntity buyTicket(@RequestBody Seat to_buy) {
        int row = to_buy.getRow();
        int column = to_buy.getColumn();
        ArrayList<Seat> seats = service.getSeats();
        if (row < 1 || row > 9 || column < 1 || column > 9) {
            throw new SeatOutOfBoundsException("The number of a row or a column is out of bounds!");
        }
        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            if (seat.getRow() == row && seat.getColumn() == column) {
                Ticket t = purchaseHelper(i);
                return ResponseEntity.ok(t);
            }
        }
        throw new TicketPurchasedException("The ticket has been already purchased!");
    }

    public Ticket purchaseHelper(int seatIndex){
        ArrayList<Seat> seats = service.getSeats();
        Seat seat = seats.get(seatIndex);

        seats.remove(seatIndex);
        service.setSeats(seats);
        profit += seat.getPrice();

        Ticket t = new Ticket(seat);
        purchased_tickets.add(t);
        return t;
    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody HashMap json){
        String token = (String) json.get("token");
        ArrayList<Seat> seats = service.getSeats();

        for(int i=0; i< purchased_tickets.size(); i++){
            Ticket temp = purchased_tickets.get(i);
            if(temp.getToken().equals(token)){
                Seat returned = returnHelper(i);

                Map<String, Object> body = new LinkedHashMap<>();
                body.put("returned_ticket", returned);
                return new ResponseEntity<>(body, HttpStatus.OK);
            }
        }
        throw new TokenNotFoundException("Wrong token!");
    }

    public Seat returnHelper(int ticketIndex){
        ArrayList<Seat> seats = service.getSeats();
        Ticket temp = purchased_tickets.get(ticketIndex);

        purchased_tickets.remove(ticketIndex);
        profit -= temp.getTicket().getPrice();

        seats.add(temp.getTicket());
        service.setSeats(seats);

        return temp.getTicket();
    }


}