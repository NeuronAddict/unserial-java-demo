package tech.woodandsafety.unserialjavademo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ProductController {

    private final TrackingService trackingService;

    public ProductController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping("/product")
    public String index(@RequestParam(required = false) String trackingData) throws IOException, ClassNotFoundException {
        return "product";
    }

    @GetMapping("/product/{id}")
    public String index(@RequestParam(required = false) String trackingData, @PathVariable("id") Integer productId) throws IOException, ClassNotFoundException {
        if(trackingData != null) trackingService.readTrackingInfo(trackingData, productId);
        return "laptop";
    }
}
