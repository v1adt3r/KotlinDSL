operator fun Int.times(s: String): Unit {
    repeat(this) {
        println(s)
    }
}

infix fun Int.abc(s: String) : Unit {

}

fun main() {
    5*"hello"

    5 abc "hello"
}