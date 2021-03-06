
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.*;


class ExpressionTests {

    @Test
    fun evaluateOnAConstantExpression_returnsCorrectValue() {
        val expectedValue = 5
        val sut = constantEvaluate(expectedValue)
        assertEquals(expectedValue, actual = sut())
    }

    @Test
    fun evaluateOnADivisionExpression_returnsCorrectValue() {
        val expectedValue = 10 / 5
        val sut = arithmeticExpressionEvaluate(opr = Operator.DIV, left = constantEvaluate(10), right = constantEvaluate(5))
        assertEquals(expectedValue, actual = sut())
    }

    @Test
    fun evaluateOnAMultiplicationExpression_returnsCorrectValue() {
        val expectedValue = 10 * 5
        val sut = arithmeticExpressionEvaluate(opr = Operator.TIMES, left = constantEvaluate(10), right = constantEvaluate(5))
        assertEquals(expectedValue, actual = sut())
    }

    @Test
    fun evaluateOnAdditionExpression_returnsCorrectValue() {
        val expectedValue = 10 + 5
        val sut = arithmeticExpressionEvaluate(opr = Operator.PLUS, left = constantEvaluate(10), right = constantEvaluate(5))
        assertEquals(expectedValue, actual = sut())
    }

    @Test
    fun evaluateOnSubtractionExpression_returnsCorrectValue() {
        val expectedValue = 10 - 5
        val sut = arithmeticExpressionEvaluate(opr = Operator.MINUS, left = constantEvaluate(10), right = constantEvaluate(5))
        assertEquals(expectedValue, actual = sut())
    }

    @Test
    fun fromStringForAdditionSymbol_returnsPLUS() {
        val sut = Operator.fromString("+")
        assertSame(Operator.PLUS, sut)
    }

    @Test
    fun fromStringForSubtractionSymbol_returnsMINUS() {
        val sut = Operator.fromString("-")
        assertSame(Operator.MINUS, sut)
    }

    @Test
    fun fromStringForMultiplicationSymbol_returnsTIMES() {
        val sut = Operator.fromString("*")
        assertSame(Operator.TIMES, sut)
    }

    @Test
    fun fromStringForDivisionSymbol_returnsDIV() {
        val sut = Operator.fromString("/")
        assertSame(Operator.DIV, sut)
    }

    @Test
    fun fromStringForInvalidSymbol_throws() {
        assertThrows<IllegalArgumentException> { Operator.fromString("%") }
    }
}