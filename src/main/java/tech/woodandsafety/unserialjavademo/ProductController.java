package tech.woodandsafety.unserialjavademo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ProductController {

    private final TrackingService trackingService;
    private final ProductService productService;

    public ProductController(TrackingService trackingService, ProductService productService) {
        this.trackingService = trackingService;
        this.productService = productService;
    }

    @GetMapping("/product")
    public String index(@RequestParam(required = false) String trackingData) throws IOException, ClassNotFoundException {
        return "products";
    }

    @GetMapping("/product/{id}")
    public ModelAndView index(@RequestParam(required = false) String trackingData, @PathVariable("id") Integer productId) throws IOException, ClassNotFoundException {
        if(trackingData != null) trackingService.readTrackingInfo(trackingData, productId);
        ModelAndView modelAndView = new ModelAndView("product");
        Product product = productService.get(productId);
        modelAndView.addObject("product", product);
        modelAndView.addObject("track", trackingData);
        modelAndView.addObject("image_src", String.format("/images/%s.jpg", product.getImage()));
        return modelAndView;
    }
}
