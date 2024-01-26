package parking

import java.util.*

var parkingLot = mutableListOf<ParkingSlot>()

private fun usedParkingSlots() = parkingLot.filter { it.car != null }
private fun filterByCarColor(it: ParkingSlot, color: String) = it.car?.color!!.uppercase() == color.uppercase()

fun main() {

    val scanner = Scanner(System.`in`)
    while (scanner.hasNext()) {
        val command = scanner.nextLine().split(" ")

        when (command[0]) {
            "create" -> createLot(command)
            "park" -> if (checkForLot()) parkCar(Car(command[1], command[2]))
            "leave" -> if (checkForLot()) leaveSpace(command[1].toInt())
            "status" -> if (checkForLot()) printLotStatus()
            "reg_by_color" -> if (checkForLot()) printRegistrationByColor(command[1])
            "spot_by_color" -> if (checkForLot()) printSlotByColor(command[1])
            "spot_by_reg" -> if (checkForLot()) printSlotByReg(command[1])
            "exit" -> break
        }
    }

}

private fun createLot(command: List<String>) {
    val lotSize = command[1].toInt()
    if (lotSize < 0) {
        println("A parking lot must have a positive number of spaces")
    } else {
        parkingLot.clear()
        for (i in 0 until lotSize) {
            parkingLot.add(ParkingSlot(i + 1, null))
        }
        println("Created a parking lot with $lotSize spots.")
    }
}

fun checkForLot(): Boolean {
    if (parkingLot.size == 0) {
        println ("Sorry, a parking lot has not been created.")
        return false
    } else {
        return true
    }
}

fun parkCar(car: Car) {
    for (i in parkingLot.indices) {
        if (parkingLot[i].car == null) {
            parkingLot[i].car = car
            println("${car.color} car parked in spot ${i + 1}.")
            return
        }
    }
    println("Sorry, the parking lot is full.")
}

fun leaveSpace(space: Int) {
    if (parkingLot[space - 1].car != null) {
        parkingLot[space - 1].car = null
        println("Spot ${space} is free.")
    } else {
        println("There is no car in spot ${space}.")
    }
}

fun printLotStatus() {

     if (usedParkingSlots().isEmpty()) {
         println("Parking lot is empty.")
         return
     }

    for (slot in usedParkingSlots()) {
        println("${slot.slotNumber} ${slot.car?.registrationID} ${slot.car?.color}")
    }
}


fun printRegistrationByColor(color: String) {

    val selectedRegistrationIDs = usedParkingSlots()
        .filter { filterByCarColor(it, color) }
        .map { it.car!!.registrationID }

    if (selectedRegistrationIDs.isEmpty()) {
        println("No cars with color ${color.uppercase()} were found.")
    } else {
        println(selectedRegistrationIDs.joinToString())
    }
}

fun printSlotByColor(color: String) {

    val selectedSlots = usedParkingSlots()
        .filter { filterByCarColor(it, color) }
        .map { it.slotNumber }

    if (selectedSlots.isEmpty()) {
        println("No cars with color ${color.uppercase()} were found.")
    } else {
        println(selectedSlots.joinToString())
    }
}

fun printSlotByReg(reg: String) {

    val selectedSlots = usedParkingSlots()
        .filter { it.car?.registrationID!! == reg }
        .map { it.slotNumber }

    if (selectedSlots.isEmpty()) {
        println("No cars with registration number $reg were found.")
    } else {
        println(selectedSlots.joinToString())
    }
}

data class ParkingSlot(
    val slotNumber: Int,
    var car: Car?
)

data class Car(
    val registrationID: String,
    val color: String
)