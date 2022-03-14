package person.v1

class Person(
    var name: String? = null,
    var age: Int? = null,
    var address: Address? = null
)

class Address(
    var street: String? = null,
    var number: Int? = null,
    var city: String? = null
)

fun person(f: Person.() -> Unit): Person = Person().apply(f)

fun Person.address(f: Address.() -> Unit) {
    val address = Address().apply(f)
    this.address = address
}

fun Person.foo(): Unit {
    name = "John"
    age = 27
}

fun main() {
    val person = person {
        name = "John"
        age  = 27
        address {
            street = "Lubertcy"
            number = 24
            city = "Moscow"
        }
    }

    println(person)
}