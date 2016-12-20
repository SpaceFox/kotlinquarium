package fr.spacefox.kotlinquarium

/**
 * Created by spacefox on 20/12/16.
 */
class Aquarium {
    val algae = mutableSetOf<Alga>()
    val fishes = mutableSetOf<Fish>()
    var age = 0

    fun addAlga(alga: Alga) {
        algae.add(alga)
    }

    fun addFish(fish: Fish) {
        fishes.add(fish)
    }

    fun newTurn() {
        age++

        // Pour l'instant on ne fait rien de particulier

        print(this)
    }

    override fun toString(): String {
        var out = "Turn $age. This aquarium contains ${algae.size} algae and ${fishes.size} fishes."
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

    metalQuarium.newTurn()
}