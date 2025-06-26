package com.example;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkServiceTest {

    @Test
    public void testServiceWithMockNetworkClient() {
        // Step 1: Mock the network client
        NetworkClient mockNetworkClient = mock(NetworkClient.class);

        // Step 2: Stub connect() method
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");

        // Step 3: Inject into service and test
        NetworkService networkService = new NetworkService(mockNetworkClient);
        String result = networkService.connectToServer();

        // Step 4: Verify result
        assertEquals("Connected to Mock Connection", result);
    }
}
