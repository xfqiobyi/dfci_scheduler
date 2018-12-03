package com.dfci.scheduler.model.serialization;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        if (jsonParser.getCurrentToken().equals(JsonToken.VALUE_STRING)) {
            return LocalDate.parse(jsonParser.getText(), ISO_LOCAL_DATE);
        }

        throw new JsonParseException(jsonParser, "Unable to parse " + jsonParser.getCurrentToken() + " as LocalDate");
    }
}
