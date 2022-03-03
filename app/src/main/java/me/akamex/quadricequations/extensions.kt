package me.akamex.quadricequations

fun headerIndex(value: String): String = "<small><sup>$value</sup></small>"

fun footerIndex(value: String): String = "<small><sub>$value</sub></small>"

fun Double.toStringValue(): String {
    if(toLong().toDouble() == this) {
        return toLong().toString()
    }
    return toDouble().toString()
}