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

    for (i in 1..4) {
        metalQuarium.addAlga(Alga())
    }
    metalQuarium.addFish(Fish("Lemmy Kilmister", Sex.MALE))
    metalQuarium.addFish(Fish("Floor Jansen", Sex.FEMALE))

    metalQuarium.newTurn()
}