package com.dfci.scheduler.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PatientTest {

    @Test
    public void deserialization_test() throws Exception {
        String json = "{\n" +
                    "\"first_name\": \"Dan\",\n" +
                    "\"last_name\": \"Pierkowski\",\n" +
                    "\"date_of_birth\": \"1900-01-01\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        Patient patient = mapper.readValue(json, Patient.class);

        Assert.assertEquals("Dan", patient.getFirstName());
        Assert.assertEquals("Pierkowski", patient.getLastName());
        Assert.assertEquals(LocalDate.of(1900, 1, 1), patient.getDateOfBirth());
    }
}
