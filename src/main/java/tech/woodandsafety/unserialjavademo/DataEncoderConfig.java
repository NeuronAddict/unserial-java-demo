package tech.woodandsafety.unserialjavademo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.woodandsafety.unserialjavademo.bean.TrackingId;
import tech.woodandsafety.unserialjavademo.tools.DataEncoder;
import tech.woodandsafety.unserialjavademo.tools.SerializeEncoder;

@Configuration
public class DataEncoderConfig {

    @Bean
    public DataEncoder<TrackingId> encoder(@Value("${tech.woodandsafety.unserial.safe}") boolean safe) {
        if(safe) return new SerializeEncoder<>(TrackingId.class);
        else return new SerializeEncoder<>();
    }

}
