package siscode.mobbgone

inline fun <reified T: Enum<T>> getEnumNames(): Set<String> {
    return enumValues<T>().map { it.name }.toSet()
}

inline fun <T> Set<T>.filter(predicate: (T) -> Boolean): Set<T> {
    return filterTo(mutableSetOf(), predicate)
}

fun globToRegex(pattern: String): Regex {
    return Regex.escape(pattern).replace("*", "\\E.*\\Q").toRegex()
}
