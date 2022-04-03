package tech.woodandsafety.unserialjavademo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tech.woodandsafety.unserialjavademo.bean.Product;
import tech.woodandsafety.unserialjavademo.service.ProductService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        Mockito.when(productService.get(1))
                .thenReturn(new Product(1, "product1", "description1", "image1"));

        Mockito.when(productService.all())
                .thenReturn(new Product[] {
                        new Product(1, "product1", "description1", "image1"),
                        new Product(2, "product2", "description2", "image2")
                });
    }

    @Autowired
    private MockMvc mvc;

    @Test
    void testProducts() throws Exception {
        mvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<div class=\"product_box\">")))
                .andExpect(content().string(containsString("<figure><img src=\"/images/image1.png\" alt=\"#\"/></figure>")))
                .andExpect(content().string(containsString("<a href=\"/product/1?trackingData=rO0ABXNyADV0ZWNoLndvb2RhbmRzYWZldHkudW5zZXJpYWxqYXZhZGVtby5iZWFuLlRyYWNraW5nSW5mbylNkYRWjgmhAgAESQAJcHJvZHVjdElkTAACaXB0ABJMamF2YS9sYW5nL1N0cmluZztMAAN1cmxxAH4AAUwACXVzZXJBZ2VudHEAfgABeHAAAAAAdAAJMTI3LjAuMC4xdAABL3A%3D\"><h3>product1</h3></a>")));
    }

    @Test
    void testProduct() throws Exception {
        mvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h2>product1</h2>")))
                .andExpect(content().string(containsString("<p>description1</p>")))
                .andExpect(content().string(containsString("<img src=\"/images/image1.png\"")))

        ;
    }

}
