import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

enum class Operator {
    PLUS, MINUS, TIMES, DIV;

    companion object {
        fun fromString(symbol: String) = when (symbol) {
            "+" -> PLUS
            "-" -> MINUS
            "*" -> TIMES
            "/" -> DIV
            else -> throw IllegalArgumentException()
        }
    }
}

class Expression(private val opr: Operator?,
                 private val left: Expression?,
                 private val right: Expression?,
                 private val value: Int = 0) {

    constructor(value: Int) : this(null, null, null, value)

    fun evaluate(): Int =
        if (left != null && right != null) {
            when (opr) {
                Operator.PLUS -> left.evaluate() + right.evaluate()
                Operator.MINUS -> left.evaluate() - right.evaluate()
                Operator.TIMES -> left.evaluate() * right.evaluate()
                Operator.DIV -> left.evaluate() / right.evaluate()
                null -> throw IllegalStateException()
            }
        }
        else value
}