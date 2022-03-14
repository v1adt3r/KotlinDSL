package person.v2

class Person(
    val name: String,
    val age: Int,
    val address: Address?
)

class Address(
    val street: String,
    val number: Int,
    val city: String
)

class PersonBuilder {
    var name: String      = ""
    var age: Int          = 0
    var address: Address? = null

    fun address(f: AddressBuilder.() -> Unit) {
        address = AddressBuilder().apply(f).build()
    }

    fun build(): Person = Person(name, age, address)
}

class AddressBuilder {
    var street: String = ""
    var number: Int    = 0
    var city: String   = ""

    fun build(): Address = Address(street, number, city)
}

fun person(f: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(f).build()

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