package fr.spacefox.kotlinquarium

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