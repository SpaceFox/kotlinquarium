package fr.spacefox.kotlinquarium

import java.util.*

/**
 * Created by spacefox on 20/12/16.
 */
// Ici une astuce : on crée une interface avec une propriété abstraite pour pouvoir l'utiliser dans les interfaces
// « Herbivore » et « Carnivore » qui ne peuvent pas hériter de classes.
interface LifeForm {
    var hp: Int
    var age: Int

    fun ages() {
        age++
    }

    fun isAlive(): Boolean {
        return hp > 0 && age <= 20
    }
}

// Et ici on implémente la propriété pour éviter d'avoir à le faire en double
abstract class LifeFormImpl(override var age: Int) : LifeForm {
    override var hp = 10
}

class Alga(age: Int) : LifeFormImpl(age) {
    override fun ages() {
        super.ages()
        hp++    // An alga grows when it ages
    }

    fun reproduces(): Alga {
        println("° $this splits and creates a new alga °")
        hp /= 2
        val child = Alga(0)
        child.hp = hp
        return child
    }

    override fun toString(): String {
        return "An alga of age $age with $hp HP."
    }
}

private val random = Random()

abstract class Fish(val name: String, val sex: Sex, age: Int) : LifeFormImpl(age), Cloneable {

    override fun ages() {
        super.ages()
        hp--    // A fish goes hungry when it ages
    }

    fun isHungry(): Boolean {
        return hp <= 5
    }

    fun reproducesWith(partner: Fish): Fish? {
        return if (this != partner && partner.javaClass == this.javaClass && partner.age > 0) {
            val childSex = Sex.values()[random.nextInt(Sex.values().size)]
            val name = "${this.name} ${partner.name}"
                    .shuffle()
                    .subSequence(0, (this.name.length + partner.name.length) / 2)
                    .toString()
            val child = when (this) {
                is Grouper      -> Grouper  (name, childSex, 0)
                is Tuna         -> Tuna     (name, childSex, 0)
                is Clownfish    -> Clownfish(name, childSex, 0)
                is Sole         -> Sole     (name, childSex, 0)
                is Bass         -> Bass     (name, childSex, 0)
                is Carp         -> Carp     (name, childSex, 0)
                else            -> null
            }
            println("° $this reproduces with $partner and gave birth to $child °")
            child
        } else {
            println("$this and $partner can't reproduces")
            null
        }
    }

    override fun toString(): String {
        return "${sex.symbol} $name (${this.javaClass.simpleName}, $hp HP, age $age)"
    }
}

private fun String.shuffle(): String {
     return String(
             this
                     .toCharArray()
                     .sortedWith(Comparator<Char> { t1, t2 -> random.nextInt(3) - 2 })
                     .toCharArray()
     ).trim()
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
class Grouper(name: String, sex: Sex, age: Int) : Fish(name, sex, age), Carnivore

// Thon
class Tuna(name: String, sex: Sex, age: Int) : Fish(name, sex, age), Carnivore

// Poisson-clown
class Clownfish(name: String, sex: Sex, age: Int) : Fish(name, sex, age), Carnivore

// Sole
class Sole(name: String, sex: Sex, age: Int) : Fish(name, sex, age), Herbivore

// Bar
class Bass(name: String, sex: Sex, age: Int) : Fish(name, sex, age), Herbivore

// Carpe
class Carp(name: String, sex: Sex, age: Int) : Fish(name, sex, age), Herbivore

enum class Sex(val symbol: String) {
    MALE("♂"),
    FEMALE("♀")
}