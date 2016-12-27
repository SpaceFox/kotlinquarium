package fr.spacefox.kotlinquarium

import java.util.*

/**
 * Created by spacefox on 20/12/16.
 */

class Aquarium {
    val algae = mutableListOf<Alga>()
    val fishes = mutableListOf<Fish>()

    private var age = 0

    fun addAlga(alga: Alga) {
        algae.add(alga)
    }

    fun addFish(fish: Fish) {
        fishes.add(fish)
    }

    fun newTurn() {
        age++

        algae.forEach(Alga::ages)
        val iterator = algae.listIterator()
        while (iterator.hasNext()) {
            val alga = iterator.next()
            if (!alga.isAlive()) {
                println("$alga is dead")
                iterator.remove()
            }
        }


        val survivorFishes: MutableList<Fish> = mutableListOf()
        survivorFishes.addAll(fishes)

        fishes
                .asSequence()   // Indispensable pour éviter le traitement de poissons mangés
                .filter { it in survivorFishes }
                .forEach {
                    it.ages()
                    if (!it.isAlive()) {
                        println("✝ $it is dead ✝")
                        survivorFishes.remove(it)
                    }
                    else if (it.isHungry()) when (it) {
                        is Carnivore -> {
                            val prey = survivorFishes.getRandom()
                            if (prey != null && it.eat(prey) && !prey.isAlive()) {
                                println("✝ $prey is dead ✝")
                                survivorFishes.remove(prey)
                            }
                        }
                        is Herbivore -> {
                            val alga = algae.getRandom()
                            if (alga != null && it.eat(alga) && !alga.isAlive()) {
                                println("An alga is dead.")
                                algae.remove(alga)
                            }
                        }
                    }
                }

        fishes.clear()
        fishes.addAll(survivorFishes)

        print(this)
    }

    override fun toString(): String {
        var out = "Turn $age. This aquarium contains ${algae.size} algae and ${fishes.size} fishes :"
        fishes.forEach { out += "\n- $it" }
        algae.forEach { out += "\n- $it" }
        return out
    }
}

private val random = Random()

fun <E> MutableList<E>.getRandom(): E? {
    return if (this.isNotEmpty()) this[random.nextInt(this.size)] else null
}

