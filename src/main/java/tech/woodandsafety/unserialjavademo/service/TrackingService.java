package tech.woodandsafety.unserialjavademo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.woodandsafety.unserialjavademo.bean.TrackingId;
import tech.woodandsafety.unserialjavademo.bean.TrackingInfo;
import tech.woodandsafety.unserialjavademo.tools.DataEncoder;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TrackingService {

    private final DataEncoder<TrackingId> encoder;
    private final Map<TrackingId, TrackingInfo> trackingInfoMap;

    public TrackingService(DataEncoder<TrackingId> encoder) {
        this.encoder = encoder;
        this.trackingInfoMap = new HashMap<>();
    }


    public String getTrackingId(String remoteAddress, String userAgent) throws DataEncoder.EncodeException {
        log.info(String.format("Got user agent : %s for address %s", userAgent, remoteAddress));
        TrackingId id = new TrackingId(remoteAddress, userAgent);

        return encoder.encode(id);
    }

    public TrackingInfo updateTrackingInfo(String encodedTrackingId, int productId) throws DataEncoder.DecodeException {
        TrackingId ti = encoder.decode(encodedTrackingId);

        if(!this.trackingInfoMap.containsKey(ti)) {
            this.trackingInfoMap.put(ti, new TrackingInfo());
        }
        TrackingInfo trackingInfo = this.trackingInfoMap.get(ti);
        trackingInfo.addProductId(productId);
        return trackingInfo;

    }
}
