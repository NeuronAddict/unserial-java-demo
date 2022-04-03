package tech.woodandsafety.unserialjavademo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tech.woodandsafety.unserialjavademo.service.TrackingService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller("/")
public class HomeController {

    private final TrackingService trackingService;

    public HomeController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping
    public String home(HttpServletRequest request, Model model) throws IOException {
        model.addAttribute("track", trackingService.getTrackingId(request.getRemoteAddr(), request.getHeader("User-Agent")));
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
