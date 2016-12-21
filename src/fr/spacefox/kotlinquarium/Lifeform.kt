package fr.spacefox.kotlinquarium

/**
 * Created by spacefox on 20/12/16.
 */
abstract class Fish(val name: String, val sex: Sex) {
    override fun toString(): String {
        return "${sex.symbol} $name (${this.javaClass.simpleName})"
    }
}

interface Carnivore {
    fun eat(prey: Fish): Boolean {
        return prey !== this
    }
}

interface Herbivore {
    fun eat(food: Alga): Boolean {
        return true
    }
}

// Mérou
class Grouper(name: String, sex: Sex) : Fish(name, sex), Carnivore

// Thon
class Tuna(name: String, sex: Sex) : Fish(name, sex), Carnivore

// Poisson-clown
class Clownfish(name: String, sex: Sex) : Fish(name, sex), Carnivore

// Sole
class Sole(name: String, sex: Sex) : Fish(name, sex), Herbivore

// Bar
class Bass(name: String, sex: Sex) : Fish(name, sex), Herbivore

// Carpe
class Carp(name: String, sex: Sex) : Fish(name, sex), Herbivore

enum class Sex(val symbol: String) {
    MALE("♂"),
    FEMALE("♀")
}