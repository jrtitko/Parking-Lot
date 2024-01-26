fun <T> getStringsOnly(list: List<T>): List<String> {
    val result = mutableListOf<String>()

    list
        .filter { it is String }
        .forEach { result.add(it.toString()) }

    return result
}