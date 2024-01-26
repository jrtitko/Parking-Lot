fun countStrings(list: List<Any>): Int {
    return list
        .filter { it is String }
        .count()
}