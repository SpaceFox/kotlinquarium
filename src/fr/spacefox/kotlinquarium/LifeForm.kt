package fr.spacefox.kotlinquarium

/**
 * Created by spacefox on 20/12/16.
 */
// Ici une astuce : on crée une interface avec une propriété abstraite pour pouvoir l'utiliser dans les interfaces
// « Herbivore » et « Carnivore » qui ne peuvent pas hériter de classes.
interface LifeForm {
    var hp: Int

    fun ages()

    fun isAlive(): Boolean {
        return hp > 0
    }
}

// Et ici on implémente la propriété pour éviter d'avoir à le faire en double
abstract class LifeFormImpl : LifeForm {
    override var hp: Int = 10
}

class Alga : LifeFormImpl() {
    override fun ages() {
        hp++    // An alga grows when it ages
    }

    override fun toString(): String {
        return "An alga with $hp HP."
    }
}

abstract class Fish(val name: String, val sex: Sex) : LifeFormImpl() {

    override fun ages() {
        hp--    // A fish goes hungry when it ages
    }

    fun isHungry(): Boolean {
        return hp <= 5
    }

    override fun toString(): String {
        return "${sex.symbol} $name (${this.javaClass.simpleName}, $hp HP)"
    }
}

interface Carnivore : LifeForm {
    fun eat(prey: Fish): Boolean {
        return if (prey.javaClass == this.javaClass) {
            println("$this tried to eat $prey, but can't because both fishes are the same race")
            false
        } else {
            this.hp += 5
            prey.hp -= 4
            println("$this bites $prey. It hurts!")
            true
        }
    }
}

interface Herbivore : LifeForm {
    fun eat(food: Alga): Boolean {
        this.hp += 3
        food.hp -= 2
        println("$this eats $food")
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