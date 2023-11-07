package controller;

import models.City;
import models.GeoLocation;
import models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.WeatherService;

@RestController
@RequestMapping("/weather/")
public class whether {

    @Autowired
    private final WeatherService weatherService;

    public whether(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @PostMapping("/yourLocation")
    public ResponseEntity<WeatherResponse> getWeather(@RequestBody GeoLocation geoLocation) {
        double lat=geoLocation.getLat();
        double lon=geoLocation.getLon();

        WeatherResponse resp= weatherService.getCurrentWeather(lat, lon);

        if (resp!=null){
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @PostMapping("/by-city-id")
    public ResponseEntity<WeatherResponse> getWeatherByCityId(@RequestBody City city) {
        long cityId = city.getCityId();
        WeatherResponse weatherResponse = weatherService.getCurrentWeatherByCityId(cityId);

        if (weatherResponse != null) {
            return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
    @PostMapping("/zipcode")
    public String getWeatherDataByZipCode(@RequestParam Integer zipCode) {

        WeatherResponse currentWeatherByZipCode = weatherService.getCurrentWeatherByZipCode(zipCode);

        if (currentWeatherByZipCode != null) {

            return String.valueOf(new ResponseEntity<>(currentWeatherByZipCode, HttpStatus.OK));

        } else {

            return String.valueOf(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

    }
}
