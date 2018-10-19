package isel.adeetc.pdm.currencyapp.network

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

class Currencies(
        val success: Boolean,
        val terms: String,
        val privacy: String,
        @JsonProperty("timestamp") val timeStamp: Long,
        val source: String,
        val quotes: Quotes)

class Quote(val currency: String, val quote: Float)

@JsonDeserialize(using = QuotesDeserializer::class)
class Quotes : ArrayList<Quote>()

class QuotesDeserializer : JsonDeserializer<Quotes>() {
    override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): Quotes {
        val quotes = Quotes()
        while(parser?.hasCurrentToken() == true) {
            parser.nextToken()
            if (parser.currentToken == JsonToken.FIELD_NAME) {
                val fieldName = parser.currentName()
                parser.nextToken()
                quotes.add(Quote(fieldName, parser.floatValue))
            }
        }
        return quotes
    }
}
