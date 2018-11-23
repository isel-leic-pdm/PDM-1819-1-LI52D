package isel.adeetc.pdm.currencyfullapp.network

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

class CurrenciesDTO(
        val success: Boolean,
        val terms: String,
        val privacy: String,
        @JsonProperty("timestamp") val timeStamp: Long,
        val source: String,
        val quotes: QuotesDTO)

class QuoteDTO(val currency: String, val quote: Float)

@JsonDeserialize(using = QuotesDeserializer::class)
class QuotesDTO : ArrayList<QuoteDTO>()

class QuotesDeserializer : JsonDeserializer<QuotesDTO>() {
    override fun deserialize(parser: JsonParser?, ctxt: DeserializationContext?): QuotesDTO {
        val quotes = QuotesDTO()
        while(parser?.hasCurrentToken() == true) {
            parser.nextToken()
            if (parser.currentToken == JsonToken.FIELD_NAME) {
                val fieldName = parser.currentName()
                parser.nextToken()
                quotes.add(QuoteDTO(fieldName, parser.floatValue))
            }
        }
        return quotes
    }
}
