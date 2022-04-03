package tech.woodandsafety.unserialjavademo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tech.woodandsafety.unserialjavademo.bean.Product;
import tech.woodandsafety.unserialjavademo.service.ProductService;
import tech.woodandsafety.unserialjavademo.service.TrackingService;

import javax.servlet.http.HttpServletRequest;
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
    public ModelAndView products(HttpServletRequest request, @RequestParam(required = false) String trackingData) throws IOException {
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("track", trackingService.getTrackingId(request.getRemoteAddr(), request.getHeader("User-Agent")));
        modelAndView.addObject("products", productService.all());
        return modelAndView;
    }

    @GetMapping("/product/{id}")
    public ModelAndView product(@RequestParam(required = false) String trackingData, @PathVariable("id") Integer productId) throws IOException, ClassNotFoundException {
        if(trackingData != null) trackingService.updateTrackingInfo(trackingData, productId);
        ModelAndView modelAndView = new ModelAndView("product");
        Product product = productService.get(productId);
        modelAndView.addObject("product", product);
        modelAndView.addObject("track", trackingData);
        modelAndView.addObject("image_src", String.format("/images/%s.jpg", product.getImage()));
        return modelAndView;
    }
}
