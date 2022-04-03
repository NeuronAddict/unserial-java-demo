package tech.woodandsafety.unserialjavademo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Controller("/")
public class HomeController {

    private final TrackingService trackingService;

    public HomeController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping
    public String home(HttpServletRequest request, Model model) throws IOException {
        log.info(String.format("Got user agent : %s", request.getHeader("User-Agent")));
        model.addAttribute("track", trackingService.getTrackingInfo(request.getRemoteAddr(), request.getHeader("User-Agent")));
        return "index";
    }
}
