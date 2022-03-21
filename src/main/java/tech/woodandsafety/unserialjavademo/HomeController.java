package tech.woodandsafety.unserialjavademo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Base64;

@Controller
public class HomeController {

    @GetMapping
    public String home(HttpServletRequest request, Model model) throws IOException {
        TrackingInfo ti = new TrackingInfo(request.getRemoteAddr(), request.getHeader("User-Agent"), "/");
        try(
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
        ){
            oos.writeObject(ti);
            String encoded = new String(Base64.getEncoder().encode(bos.toByteArray()));
            model.addAttribute("track", encoded);
        }
        return "index";
    }
}
