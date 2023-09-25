package guru.qa.geoservice.service;

import guru.qa.geoservice.data.GeoRepository;
import guru.qa.geoservice.domain.GeoJson;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoService {

    private final GeoRepository geoRepository;

    @Autowired
    public GeoService(GeoRepository geoRepository) {
        this.geoRepository = geoRepository;
    }

    public List<GeoJson> getAllGeo() {
        return IterableUtils.toList(geoRepository.findAll())
                .stream()
                .map(GeoJson::fromEntity)
                .collect(Collectors.toList());
    }

}
