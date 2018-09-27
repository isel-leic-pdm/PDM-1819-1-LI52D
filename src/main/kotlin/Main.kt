
fun main(args: Array<String>) {
    val input = readLine()
    if (input == null) {
        print("Please enter a valid expression")
        return
    }
    print("$input = ${parse(input).evaluate()}")
}