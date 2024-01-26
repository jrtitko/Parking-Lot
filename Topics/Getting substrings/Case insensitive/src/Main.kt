import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val line1 = scanner.nextLine()
    val line2 = scanner.nextLine()

    println(line1.uppercase() == line2.uppercase())
}