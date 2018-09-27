import java.lang.IllegalArgumentException

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

interface Expression {
    fun evaluate(): Int
}

class ArithmeticExpression(private val opr: Operator,
                           private val left: Expression,
                           private val right: Expression) : Expression {

    override fun evaluate(): Int =
        when (opr) {
            Operator.PLUS -> left.evaluate() + right.evaluate()
            Operator.MINUS -> left.evaluate() - right.evaluate()
            Operator.TIMES -> left.evaluate() * right.evaluate()
            Operator.DIV -> left.evaluate() / right.evaluate()
        }
}

class Constant(val value: Int) : Expression {
    override fun evaluate() = value
}