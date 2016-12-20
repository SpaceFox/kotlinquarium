package fr.spacefox.kotlinquarium

import java.util.*

/**
 * Created by spacefox on 20/12/16.
 */
class Aquarium {
    val algae = mutableListOf<Alga>()
    val fishes = mutableListOf<Fish>()

    private var age = 0
    private val random = Random()

    fun addAlga(alga: Alga) {
        algae.add(alga)
    }

    fun addFish(fish: Fish) {
        fishes.add(fish)
    }

    fun newTurn() {
        age++

        val survivorFishes: MutableList<Fish> = mutableListOf()
        survivorFishes.addAll(fishes)

        fishes
                .asSequence()   // Indispensable pour éviter le traitement de poissons mangés
                .filter { it in survivorFishes }
                .forEach {
                    when (it) {
                        is Carnivore -> {
                            val prey = getRandomElement(survivorFishes)
                            if (prey != null && it.eat(prey)) {
                                println("$it eats $prey")
                                survivorFishes.remove(prey)
                            }
                        }
                        is Herbivore -> {
                            val alga = getRandomElement(algae)
                            if (alga != null && it.eat(alga)) {
                                println("$it eats an alga")
                                algae.remove(alga)
                            }
                        }
                    }
                }

        fishes.clear()
        fishes.addAll(survivorFishes)

        print(this)
    }

    private fun <E> getRandomElement(list: List<E>): E? {
        return if (list.isNotEmpty()) list[random.nextInt(list.size)] else null
    }

    override fun toString(): String {
        var out = "Turn $age. This aquarium contains ${algae.size} algae and ${fishes.size} fishes :"
        fishes.forEach { out += "\n- $it" }
        return out
    }
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