package tech.woodandsafety.unserialjavademo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tech.woodandsafety.unserialjavademo.bean.Product;
import tech.woodandsafety.unserialjavademo.service.ProductService;
import tech.woodandsafety.unserialjavademo.service.TrackingService;
import tech.woodandsafety.unserialjavademo.tools.DataEncoder;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {

    private final TrackingService trackingService;
    private final ProductService productService;

    public ProductController(TrackingService trackingService, ProductService productService) {
        this.trackingService = trackingService;
        this.productService = productService;
    }

    @GetMapping("/product")
    public ModelAndView products(HttpServletRequest request, @RequestParam(required = false) String trackingData) throws DataEncoder.EncodeException {
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("track", trackingService.getTrackingId(request.getRemoteAddr(), request.getHeader("User-Agent")));
        modelAndView.addObject("products", productService.all());
        return modelAndView;
    }

    @GetMapping("/product/{id}")
    public ModelAndView product(@RequestParam(required = false) String encodedTrackingId, @PathVariable("id") Integer productId) throws DataEncoder.DecodeException {
        if(encodedTrackingId != null) trackingService.updateTrackingInfo(encodedTrackingId, productId);
        ModelAndView modelAndView = new ModelAndView("product");
        Product product = productService.get(productId);
        modelAndView.addObject("product", product);
        modelAndView.addObject("track", encodedTrackingId);
        modelAndView.addObject("image_src", String.format("/images/%s.jpg", product.getImage()));
        return modelAndView;
    }
}
