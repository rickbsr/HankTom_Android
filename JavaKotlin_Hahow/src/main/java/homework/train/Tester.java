package homework.train;


import homework.train.vo.Ticket;

import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {

        do {
            try {
                Scanner scan = new Scanner(System.in);

                System.out.print("Please enter number of tickets: ");
                int total = scan.nextInt();
                if (total == -1) {
                    System.out.println("Stop booking.");
                    break;
                } else if (total < 0) throw new Exception();

                System.out.print("How many round-trip tickets:");
                int roundTrip = scan.nextInt();
                if (roundTrip < 0 || total < roundTrip) throw new Exception();

                Ticket ticket = new Ticket(total, roundTrip);
                System.out.printf("Total tickets: %d\nRound-trip: %d\nTotal: %d",
                        ticket.getTotalTickets(), ticket.getRoundTrip(), ticket.calcPrice());

            } catch (Exception e) {
                System.out.println("Error! Please try again.");
                continue;
            }
        } while (true);
    }
}
