package tech.woodandsafety.unserialjavademo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.woodandsafety.unserialjavademo.bean.TrackingInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackingServiceTest {

    private TrackingService trackingService;

    @BeforeEach
    public void setUp() {
        trackingService = new TrackingService(true);
    }

    @Test
    public void testTrackingIdGet() throws IOException, ClassNotFoundException {
        String base64 = this.trackingService.getTrackingId("127.0.0.1", "mozilla");
        this.trackingService.updateTrackingInfo(base64, 1);
        TrackingInfo ti = this.trackingService.updateTrackingInfo(base64, 2);

        assertEquals(ti.getVisited().size(), 2);
        assertEquals(ti.getVisited().get(0), 1);
        assertEquals(ti.getVisited().get(1), 2);

    }
}