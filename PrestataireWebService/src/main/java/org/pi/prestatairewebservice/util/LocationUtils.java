package org.pi.prestatairewebservice.util;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.pi.prestatairewebservice.dto.IpApiResponse;

public class LocationUtils {

    public static IpApiResponse getUserLocation() {
        Client client = ClientBuilder.newClient();
        IpApiResponse response = client
                .target("http://ip-api.com/json")
                .request(MediaType.APPLICATION_JSON)
                .get(IpApiResponse.class);
        client.close();
        return response;
    }
}
