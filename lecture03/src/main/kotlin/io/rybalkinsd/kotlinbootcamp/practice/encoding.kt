package io.rybalkinsd.kotlinbootcamp.practice

/**
 * NATO phonetic alphabet
 */
val alphabet = setOf("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliett", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "Xray", "Yankee", "Zulu")

/**
 * A mapping for english characters to phonetic alphabet.
 * [ a -> Alfa, b -> Bravo, ...]
 */
val association: Map<Char, String> = alphabet.associateBy { it[0].toLowerCase() }

/**
 * Extension function for String which encode it according to `association` mapping
 *
 * @return encoded string
 *
 * Example:
 * "abc".encode() == "AlfaBravoCharlie"
 *
 */
fun String.encode(): String = map { it.toLowerCase() }.map { association[it] ?: it }.joinToString("")

/**
 * A reversed mapping for association
 * [ alpha -> a, bravo -> b, ...]
 */

/**
 * Extension function for String which decode it according to `reversedAssociation` mapping
 *
 * @return encoded string or null if it is impossible to decode
 *
 * Example:
 * "alphabravocharlie".decode() == "abc"
 * "charliee".decode() == null
 *
 */
var fromMap: String = ""
fun decodeRecursive(pref: String, suf: String, acc: String): String {
    if (suf.isEmpty()) {
        return acc
    } else {
        if (association.containsValue(pref)) {
            fromMap = fromMap + pref[0].toString().toLowerCase()
            return pref[0].toString().toLowerCase() + decodeRecursive("", suf, acc.replace(pref, ""))
        } else {
            if (association.containsValue(suf)) {
                fromMap = fromMap + suf[0].toString().toLowerCase()
                return acc.replace(suf, "") + suf[0].toString().toLowerCase()
            } else {
                return decodeRecursive(pref + suf.first(), suf.slice(1..suf.length - 1), acc + suf.first())
            }
        }
    }
}

fun onlyAlphChars(str: String) = str.fold(true) { acc, el -> (if (el !in '0'..'9') true else false) and acc }

fun String.decode(): String? {
    val result = decodeRecursive("", this, "")
    val res = result.filter { it !in fromMap }
    if (res.isEmpty())
        return result
    if (onlyAlphChars(res) or fromMap.isEmpty())
        return null
    return result
}
