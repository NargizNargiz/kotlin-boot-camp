package io.rybalkinsd.kotlinbootcamp.practice

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

/**
 * "8".toBinary() == "1000"
 *
 * @throws NumberFormatException if could not be parsed as Number
 * @throws IllegalArgumentException if target string is null
 */
fun exThrower(){
    throw NumberFormatException("wrong format")
}
fun String?.toBinary(): String = when(this) {
    null ->   throw IllegalArgumentException()
    else -> toBigInteger(2).toString(2)
}
