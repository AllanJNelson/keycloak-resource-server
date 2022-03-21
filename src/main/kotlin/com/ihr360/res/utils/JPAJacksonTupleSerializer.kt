package com.ihr360.res.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.io.IOException
import javax.persistence.Tuple

/**
 * Serializes JPA Tuple into {key:value} json object string
 * JPA EntityManager returns data as array of tuples as result value
 * This serializer will automatically get the key names of tuple and just write as {key:value} style into JSON
 * Useful for generating JSON response for RESTful apis
 */
class JPAJacksonTupleSerializer @JvmOverloads constructor(t: Class<Tuple>? = null): StdSerializer<Tuple>(t) {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(t: Tuple, jgen: JsonGenerator, provider: SerializerProvider) {
        jgen.writeStartObject()
        t.elements.forEach{
            jgen.writeObjectField(it.alias, t.get(it.alias))
        }
        jgen.writeEndObject()
    }
}