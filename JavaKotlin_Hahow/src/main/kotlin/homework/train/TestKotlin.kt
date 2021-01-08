package homework.train

fun main(args: Array<String>) {

    do {
        try {
            print("Please enter number of tickets: ")
            var total: Int = readLine()!!.toInt()
            if (total == -1) {
                println("Stop booking.")
                break
            } else if (total <= 0) throw Exception()

            print("How many round-trip tickets: ")
            var roundTrip = readLine()!!.toInt()
            if (roundTrip < 0 || total < roundTrip) throw Exception()

            var tk = TicketKotlin(total, roundTrip)
            println("Total tickets: $total\nRound-trip: $roundTrip\nTotal: ${tk.calcPrice()}")

        } catch (e: Exception) {
            println("Error! Please try again.")
            continue
        }
    } while (true)
}

class TicketKotlin(var totalTickets: Int, var roundTrip: Int) {

    fun calcPrice(): Int {
        return (totalTickets - roundTrip) * 1000 + roundTrip * (2000.0 * 0.9).toInt()
    }
}
