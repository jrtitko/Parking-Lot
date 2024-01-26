import java.util.*

fun main() {

    val scanner = Scanner(System.`in`)

    val parameter = scanner.nextLine()
    val value = scanner.nextInt()

    when (parameter) {
        "amount" -> calculateInterest(amount = value)
        "percent" -> calculateInterest(percent = value)
        "years" -> calculateInterest(years = value)
    }
}

fun calculateInterest(amount: Int = 1000, percent: Int = 5, years: Int = 10) {
    println((amount * Math.pow(1.0 + percent / 100.0, years.toDouble())).toInt())
}
