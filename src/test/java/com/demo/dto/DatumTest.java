package com.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatumTest {
    @Test
    void objectCreation() {
        Datum datum = new Datum();
        datum.setId("1");
        datum.setTag("tag");
        datum.setValue("value");
        assertNotNull(datum.getId());
        assertNotNull(datum.getTag());
        assertNotNull(datum.getValue());
    }
}