package com.example;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    @Test
    public void testServiceWithMockRepository() {
        // Create mock of Repository
        Repository mockRepository = mock(Repository.class);

        // Stub method
        when(mockRepository.getData()).thenReturn("Mock Data");

        // Use the mock in Service
        Service service = new Service(mockRepository);
        String result = service.processData();

        // Assert the expected result
        assertEquals("Processed Mock Data", result);
        System.out.println(result);

    }
}
