package guru.qa.geoservice.service;

import guru.qa.geoservice.data.GeoRepository;
import guru.qa.geoservice.data.entity.GeoEntity;
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

    public GeoEntity addGeo(GeoJson geoJson) {
        var geoEntity = geoRepository.findAll()
                .stream()
                .filter(geo -> geo.getCountryName().equals(geoJson.countryName())
                        || geo.getCountryCode().equals(geoJson.countryCode()))
                .findFirst();
        if (geoEntity.isEmpty()) {
            GeoEntity entity = new GeoEntity();
            entity.setCountryName(geoJson.countryName());
            entity.setCountryCode(geoJson.countryCode());
            return geoRepository.save(entity);
        } else throw new RuntimeException("Country or code are already exist");
    }

    public GeoEntity updateGeoName(String oldName, String newName) {
        var geoEntity = geoRepository.findAll()
                .stream()
                .filter(geo -> geo.getCountryName().equals(oldName))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("No country with name " + oldName));
        geoEntity.setCountryName(newName);
        return geoRepository.save(geoEntity);
    }
}
