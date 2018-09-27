import java.util.NoSuchElementException

fun getTokens(exp: String) =
        exp.split(' ', '\t', '\n', '\r').map { it.trim() }.toMutableList()

fun constant(token: String): Int? =  token.toIntOrNull()

fun expression(tokens: MutableList<String>): Expression {
    if (tokens.isEmpty())
        throw NoSuchElementException()

    val value = constant(tokens.first())
    return if (value != null) { tokens.removeAt(0); Expression(value) }
    else arithmeticExpression(tokens)
}

fun arithmeticExpression(tokens: MutableList<String>): Expression {
    val operator = Operator.fromString(tokens.removeAt(0))
    return Expression(operator, expression(tokens), expression(tokens))
}

fun parse(exp: String): Expression {
    val tokens = getTokens(exp)
    return expression(tokens)
}