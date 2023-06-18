package cinema.src;

public class Seat {
    private int row, column;
    private int price;
    public Seat(){
        this.row=0;
        this.column=0;
        this.price=0;
    }
    public Seat(int row, int column, int price){
        this.row=row;
        this.column=column;
        this.price=price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
