package cinema.src;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@JsonPropertyOrder({
        "total_rows",
        "total_columns",
        "available_seats"
})
public class SeatService {
    @JsonProperty("available_seats")
    private ArrayList<Seat> seats = new ArrayList<Seat>();
    @JsonProperty("total_rows")
    private int rows=9;
    @JsonProperty("total_columns")
    private int cols=9;

    public SeatService(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                Seat temp = new Seat(i+1, j+1, getPrice(i+1));
                seats.add(temp);
            }
        }
    }
    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    private int getPrice(int row){
        if(row<=4){
            return 10;
        } else {
            return 8;
        }
    }
}
