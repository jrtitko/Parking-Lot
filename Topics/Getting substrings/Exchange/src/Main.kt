import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val input = scanner.nextLine()

    println(input.last() + input.substring(1, input.lastIndex) + input.first())
}