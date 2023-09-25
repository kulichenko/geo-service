package guru.qa.geoservice.controller;

import guru.qa.geoservice.domain.GeoJson;
import guru.qa.geoservice.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static guru.qa.geoservice.domain.GeoJson.fromEntity;

@RestController
@RequestMapping("/geo")
public class GeoController {
    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("")
    public List<GeoJson> getAllGeo() {
        return geoService.getAllGeo();
    }

    /*
    example:
    POST:http://localhost:9002/geo/update
    send json body
    {
    "countryName": "Krokozhiya",
    "countryCode":"KRZ"
    }
     */
    @PostMapping(
            path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public GeoJson addGeo(@RequestBody GeoJson geoJson) {
        return fromEntity(geoService.addGeo(geoJson));
    }

    /*
    example
    PATCH http://localhost:9002/geo/update?oldName=Kosovo&newName=Serbiya
     */

    @PatchMapping(path = "update")
    public GeoJson updateCountryName(@RequestParam String oldName, @RequestParam String newName) {
        return fromEntity(geoService.updateGeoName(oldName, newName));
    }
}
