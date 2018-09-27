
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.*;


class ExpressionTests {

    @Test
    fun evaluateOnAConstantExpression_returnsCorrectValue() {
        val expectedValue = 5
        val sut = Constant(expectedValue)
        assertEquals(expectedValue, actual = sut.evaluate())
    }

    @Test
    fun evaluateOnADivisionExpression_returnsCorrectValue() {
        val expectedValue = 10 / 5
        val sut = ArithmeticExpression(opr = Operator.DIV, left = Constant(10), right = Constant(5))
        assertEquals(expectedValue, actual = sut.evaluate())
    }

    @Test
    fun evaluateOnAMultiplicationExpression_returnsCorrectValue() {
        val expectedValue = 10 * 5
        val sut = ArithmeticExpression(opr = Operator.TIMES, left = Constant(10), right = Constant(5))
        assertEquals(expectedValue, actual = sut.evaluate())
    }

    @Test
    fun evaluateOnAdditionExpression_returnsCorrectValue() {
        val expectedValue = 10 + 5
        val sut = ArithmeticExpression(opr = Operator.PLUS, left = Constant(10), right = Constant(5))
        assertEquals(expectedValue, actual = sut.evaluate())
    }

    @Test
    fun evaluateOnSubtractionExpression_returnsCorrectValue() {
        val expectedValue = 10 - 5
        val sut = ArithmeticExpression(opr = Operator.MINUS, left = Constant(10), right = Constant(5))
        assertEquals(expectedValue, actual = sut.evaluate())
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