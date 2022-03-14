package json.v1

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

class JsonObjectBuilder {
    val jsonObj = JsonObject()

    infix fun String.to(value: String) {
        jsonObj.addProperty(this, value)
    }

    infix fun String.to(value: JsonElement) {
        jsonObj.add(this, value)
    }
}

class JsonArrayBuilder {
    val arr = JsonArray()

    operator fun String.unaryPlus() {
        arr.add(this)
    }
}


fun json(block: JsonObjectBuilder.() -> Unit): JsonObject {
    val builder = JsonObjectBuilder()
    builder.block()

    return builder.jsonObj
}

fun array(block: JsonArrayBuilder.() -> Unit): JsonArray {
    val builder = JsonArrayBuilder()
    builder.block()

    return builder.arr
}

fun main() {

    val j = json {
        "course" to "Kotlin"
        "data" to json {
            "from" to "03-02-2022"
            "to" to "14-02-2022"
        }
        "students" to array {
            +"one"
            +"two"
            +"three"
        }
    }

    println(j)
}