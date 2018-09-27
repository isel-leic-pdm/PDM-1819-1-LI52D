import java.lang.IllegalArgumentException

enum class Operator(val evaluate: (Int, Int) -> Int) {
    PLUS({ left, right -> left + right }),
    MINUS({ left, right -> left - right }),
    TIMES({ left, right -> left * right }),
    DIV({ left, right -> left / right });

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

typealias Expression = () -> Int

fun constantEvaluate(value: Int) = { value }

fun arithmeticExpressionEvaluate(opr: Operator,
                                 left: Expression,
                                 right: Expression) = {
    opr.evaluate(left(), right())
}

