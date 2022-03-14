package intro

class Student(var name: String, var age: Int) {
    val nick: MutableList<String> = mutableListOf()

    operator fun String.unaryPlus() {
        nick.add(this)
    }
}

fun Student.add(i: Int): Student {
    age += i
    return this;
}

operator fun Student.plus(i: Int): Student {
    age += i
    return this
}

fun student(f: Student.() -> Unit): Student {
    val student = Student("", 0)

    student.f()

    return student
}

fun studentApply(f: Student.() -> Unit): Student = Student("", 0).apply(f)

fun main()
{
    val s = Student("John Smit", 27)
    s + 3

    val st = student {
        name = "John"
        age  = 17
        +"red"
    }

    println(st.nick[0])
}