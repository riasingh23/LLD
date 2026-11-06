package booking.system;

public class Ticket {
    private String ticketId;
    private int row;
    private int col;
    private boolean isAvailable;

    public synchronized boolean markReserve(){
        if(!isAvailable){return false;}
        isAvailable = false;
        return true;
    }

    public synchronized boolean markAvailable(){
        if(isAvailable){ return false;}
        isAvailable = true;
        return true;
    }

}

//-ticketId
//-row
//-Column
//-isReserved
//+markReserve()
//+markUnrevered()
