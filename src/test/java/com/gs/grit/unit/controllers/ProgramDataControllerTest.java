package com.gs.grit.unit.controllers;

import com.gs.grit.controllers.ProgramData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProgramDataControllerTest {

    @Test
    public void testConstructorWithStatus() {
        // Arrange
        Integer programId = 1;
        String programName = "Program A";
        String status = "ACTIVE";

        // Act
        ProgramData programData = new ProgramData(programId, programName, status);

        // Assert
        assertEquals(programId, programData.getProgramId());
        assertEquals(programName, programData.getProgramName());
        assertEquals(status, programData.getStatus());
    }

    @Test
    public void testConstructorWithoutStatus() {
        // Arrange
        Integer programId = 2;
        String programName = "Program B";

        // Act
        ProgramData programData = new ProgramData(programId, programName);

        // Assert
        assertEquals(programId, programData.getProgramId());
        assertEquals(programName, programData.getProgramName());
        assertNull(programData.getStatus());
    }

    @Test
    public void testDefaultConstructor() {
        // Act
        ProgramData programData = new ProgramData();

        // Assert
        assertNull(programData.getProgramId());
        assertNull(programData.getProgramName());
        assertNull(programData.getStatus());
    }

    @Test
    public void testSetterAndGetterForStatus() {
        // Arrange
        ProgramData programData = new ProgramData();
        String status = "INACTIVE";

        // Act
        programData.setStatus(status);

        // Assert
        assertEquals(status, programData.getStatus());
    }

    @Test
    public void testConstructorWithStatus() {
        // Arrange
        Integer programId = 1;
        String programName = "Program A";
        String status = "ACTIVE";

        // Act
        ProgramData programData = new ProgramData(programId, programName, status);

        // Assert
        assertEquals(programId, programData.getProgramId());
        assertEquals(programName, programData.getProgramName());
        assertEquals(status, programData.getStatus());
    }

    @Test
    public void testConstructorWithoutStatus() {
        // Arrange
        Integer programId = 2;
        String programName = "Program B";

        // Act
        ProgramData programData = new ProgramData(programId, programName);

        // Assert
        assertEquals(programId, programData.getProgramId());
        assertEquals(programName, programData.getProgramName());
        assertNull(programData.getStatus());
    }

    @Test
    public void testDefaultConstructor() {
        // Act
        ProgramData programData = new ProgramData();

        // Assert
        assertNull(programData.getProgramId());
        assertNull(programData.getProgramName());
        assertNull(programData.getStatus());
    }

    @Test
    public void testSetterAndGetterForStatus() {
        // Arrange
        ProgramData programData = new ProgramData();
        String status = "INACTIVE";

        // Act
        programData.setStatus(status);

        // Assert
        assertEquals(status, programData.getStatus());
    }

    @Test
    public void testSetterAndGetterForProgramId() {
        // Arrange
        ProgramData programData = new ProgramData();
        Integer programId = 3;

        // Act
        programData.setProgramId(programId);

        // Assert
        assertEquals(programId, programData.getProgramId());
    }

    @Test
    public void testSetterAndGetterForProgramName() {
        // Arrange
        ProgramData programData = new ProgramData();
        String programName = "Program C";

        // Act
        programData.setProgramName(programName);

        // Assert
        assertEquals(programName, programData.getProgramName());
    }
}
