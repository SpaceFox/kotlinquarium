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

fun main(args: Array<String>) {
    val metalQuarium = Aquarium()

    for (i in 1..10) {
        metalQuarium.addAlga(Alga(i))
    }
    metalQuarium.addFish(Grouper("Lemmy Kilmister", Sex.MALE, 1))
    metalQuarium.addFish(Grouper("Floor Jansen", Sex.FEMALE, 12))

    metalQuarium.addFish(Tuna("James Hetfield", Sex.MALE, 2))
    metalQuarium.addFish(Tuna("Tarja Turunen", Sex.FEMALE, 11))

    metalQuarium.addFish(Clownfish("Dani Filth", Sex.MALE, 3))
    metalQuarium.addFish(Clownfish("Simone Simons", Sex.FEMALE, 10))

    metalQuarium.addFish(Sole("Ozzy Osbourne", Sex.MALE, 4))
    metalQuarium.addFish(Sole("Cammie Gilbert", Sex.FEMALE, 9))

    metalQuarium.addFish(Bass("Mikael Åkerfeldt", Sex.MALE, 5))
    metalQuarium.addFish(Bass("中元 すず香", Sex.FEMALE, 8))

    metalQuarium.addFish(Carp("Joakim Brodén", Sex.MALE, 6))
    metalQuarium.addFish(Carp("Мари́я Архипова", Sex.FEMALE, 7))

    var input: String?
    do {
        metalQuarium.newTurn()
        println("\nq to quit, <enter> for a new turn.")
        input = readLine()
    } while (input?.trim()?.toLowerCase() != "q")
}