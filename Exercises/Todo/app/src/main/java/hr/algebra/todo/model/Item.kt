package hr.algebra.todo.model

private const val DELIM = "|"

data class Item(val text: String, var done: Boolean) {
    fun prepareForFileLine() =
        String.format("%s%s%b\n", text, DELIM, done)

    companion object {
        fun parseFromFileLine(line: String): Item =
            line.split(DELIM).let {
                Item(it[0], it[1].toBoolean())
            }
    }
}
