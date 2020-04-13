package sisbase.mobbgone

fun getNames(e: Class<out Enum<*>>): Set<String> {
    val enumvalues = e.enumConstants
    return enumvalues.map { it.name }.toSet()
}

inline fun <T> Set<T>.filter(predicate: (T) -> Boolean): Set<T> {
    return filterTo(mutableSetOf(), predicate)
}
