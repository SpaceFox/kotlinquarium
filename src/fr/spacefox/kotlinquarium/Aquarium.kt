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

        val survivorFishes: MutableList<Fish> = mutableListOf()
        survivorFishes.addAll(fishes)

        fishes
                .asSequence()   // Indispensable pour éviter le traitement de poissons mangés
                .filter { it in survivorFishes }
                .forEach {
                    it.ages()
                    if (it.isHungry()) when (it) {
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
        metalQuarium.addAlga(Alga())
    }
    metalQuarium.addFish(Grouper("Lemmy Kilmister", Sex.MALE))
    metalQuarium.addFish(Grouper("Floor Jansen", Sex.FEMALE))

    metalQuarium.addFish(Tuna("James Hetfield", Sex.MALE))
    metalQuarium.addFish(Tuna("Tarja Turunen", Sex.FEMALE))

    metalQuarium.addFish(Clownfish("Dani Filth", Sex.MALE))
    metalQuarium.addFish(Clownfish("Simone Simons", Sex.FEMALE))

    metalQuarium.addFish(Sole("Ozzy Osbourne", Sex.MALE))
    metalQuarium.addFish(Sole("Cammie Gilbert", Sex.FEMALE))

    metalQuarium.addFish(Bass("Mikael Åkerfeldt", Sex.MALE))
    metalQuarium.addFish(Bass("中元 すず香", Sex.FEMALE))

    metalQuarium.addFish(Carp("Joakim Brodén", Sex.MALE))
    metalQuarium.addFish(Carp("Мари́я Архипова", Sex.FEMALE))

    var input: String?
    do {
        metalQuarium.newTurn()
        println("\nq to quit, <enter> for a new turn.")
        input = readLine()
    } while (input?.trim()?.toLowerCase() != "q")
}