package html

open class Tag(val name: String, val value: String) {
    var children: MutableList<Tag> = mutableListOf()

    fun add(tag: Tag) = children.add(tag)

    override fun toString(): String {
        return "<${name}>${value}${children.joinToString(separator = " ")}</${name}"
    }
}

class TABLE(value: String) : Tag("TABLE", value)
class TR(value: String) : Tag("TR", value)
class TD(value: String) : Tag("TD", value)

open class TagBuilder {
    var value: String          = ""
    var tags: MutableList<Tag> = mutableListOf()

    operator fun String.unaryPlus() {
        value = this
    }
}

class TDBuilder: TagBuilder() {
    fun build(): TD = TD(value).apply{
        tags.forEach{ add(it) }
    }
}

class TRBuilder: TagBuilder() {
    fun td(block: TDBuilder.() -> Unit) {
        val t = TDBuilder().apply(block).build()
        tags.add(t)
    }

    fun build(): TR = TR(value).apply{
        tags.forEach{ add(it) }
    }
}

class TableBuilder : TagBuilder() {
    fun tr(block: TRBuilder.() -> Unit) {
        val t = TRBuilder().apply(block).build()
        tags.add(t)
    }

    fun build(): TABLE = TABLE(value).apply{
        tags.forEach{ add(it) }
    }
}

fun table(block: TableBuilder.() -> Unit): TABLE = TableBuilder().apply(block).build()

fun main() {
    val html = table {
        tr {
            td {
                +"1"
            }
        }
    }

    println(html)
}