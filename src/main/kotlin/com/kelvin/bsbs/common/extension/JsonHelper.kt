package com.kelvin.bsbs.common.extension

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kelvin.bsbs.common.constant.ISO_8061_TIMESTAMP
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class JsonHelper {
    companion object {
        private val objectMapper = object : ThreadLocal<ObjectMapper>() {
            override fun initialValue(): ObjectMapper {
                val objectMapper = jacksonObjectMapper()

                val formatter = DateTimeFormatter.ofPattern(ISO_8061_TIMESTAMP)
                val localDateTimeSerializer = LocalDateTimeSerializer(formatter)
                val localDateTimeDeserializer = LocalDateTimeDeserializer(formatter)

                val javaTimeModule = JavaTimeModule()
                javaTimeModule.addSerializer(LocalDateTime::class.java, localDateTimeSerializer)
                javaTimeModule.addDeserializer(LocalDateTime::class.java, localDateTimeDeserializer)

                objectMapper.registerModule(javaTimeModule)

                return objectMapper
            }
        }

        fun getObjectMapper(): ObjectMapper = objectMapper.get()

        fun toJson(value: Any, isPrettyPrint: Boolean = false): String {
            return if (isPrettyPrint) getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(value)
            else getObjectMapper().writeValueAsString(value)
        }

        fun toJsonByteArray(value: Any): ByteArray {
            return getObjectMapper().writeValueAsBytes(value)
        }

        inline fun <reified T : Any> fromJson(content: String): T {
            return getObjectMapper().readValue(content)
        }

        inline fun <reified T : Any> fromJson(content: ByteArray): T {
            return getObjectMapper().readValue(content)
        }
    }
}
fun String.getValue(key: String): Any? {
    val a = JsonHelper.getObjectMapper().readValue(this, HashMap::class.java)
    return a[key]
}
