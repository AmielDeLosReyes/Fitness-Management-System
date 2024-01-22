package com.gs.grit.integration;

import com.gs.grit.controllers.ProgramData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramDataTest {

    @Test
    void testProgramDataWithStatus() {
        // Given
        Integer programId = 1;
        String programName = "Test Program";
        String status = "ACTIVE";

        // When
        ProgramData programData = new ProgramData(programId, programName, status);

        // Then
        assertEquals(programId, programData.getProgramId());
        assertEquals(programName, programData.getProgramName());
        assertEquals(status, programData.getStatus());
    }

    @Test
    void testProgramDataWithoutStatus() {
        // Given
        Integer programId = 2;
        String programName = "Another Program";

        // When
        ProgramData programData = new ProgramData(programId, programName);

        // Then
        assertEquals(programId, programData.getProgramId());
        assertEquals(programName, programData.getProgramName());
        assertEquals(null, programData.getStatus());
    }

    @Test
    void testSetStatus() {
        // Given
        ProgramData programData = new ProgramData();

        // When
        programData.setStatus("PENDING");

        // Then
        assertEquals("PENDING", programData.getStatus());
    }

}

