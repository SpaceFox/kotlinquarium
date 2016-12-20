package fr.spacefox.kotlinquarium

/**
 * Created by spacefox on 20/12/16.
 */
class Fish(val name: String, val sex: Sex) {
    override fun toString(): String {
        return "${sex.symbol} $name"
    }
}

enum class Sex(val symbol: String) {
    MALE("♂"),
    FEMALE("♀")
}