package guru.qa.geoservice.controller;

import guru.qa.geoservice.domain.GeoJson;
import guru.qa.geoservice.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeoController {
    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/geo")
    public List<GeoJson> getAllGeo() {
        return geoService.getAllGeo();
    }

}
