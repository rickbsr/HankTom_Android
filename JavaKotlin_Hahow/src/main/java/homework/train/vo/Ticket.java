package homework.train.vo;

public class Ticket {

    int totalTickets; // 購買票數
    int roundTrip; // 來回票

    public Ticket(int totalTickets, int roundTrip) {
        this.totalTickets = totalTickets;
        this.roundTrip = roundTrip;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getRoundTrip() {
        return roundTrip;
    }

    public int calcPrice() {
        return (int) ((totalTickets - roundTrip) * 1000 + roundTrip * 2000 * 0.9);
    }
}
