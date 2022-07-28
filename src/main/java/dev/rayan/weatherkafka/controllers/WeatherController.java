package dev.rayan.weatherkafka.controllers;

import dev.rayan.weatherkafka.classes.Weather;
import dev.rayan.weatherkafka.repository.WeatherCRUD;
import dev.rayan.weatherkafka.services.WeatherProducerService;
import dev.rayan.weatherkafka.services.WeatherConsumerService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManager.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

  @Autowired
  private WeatherProducerService weatherProducerService;
  private WeatherConsumerService weatherConsumerService;
  // private WeatherCRUD weatherCRUD;
  private EntityManager em;

  // POST /weather/create
  @PostMapping(value = "/create")
  public Weather createWeather(@RequestBody Weather weather) {

    weather = weatherProducerService.createWeather(weather);
    return weather;
  }

  /*
   * Endpoint to get latest weather's data of all location
   *  SELECT location, degree
   *  FROM locations_weather
   *  WHERE id IN (
   *    SELECT MAX(id)
   *    FROM locations_weather
   *    GROUP BY location
   *  );
   */

  // GET /weather/latestWeather
  @GetMapping("/latest")
  public List<Weather> latestWeather() {

    Query queryResult = (Query) em.createNativeQuery(
      "SELECT location, degree FROM locations_weather WHERE id IN (SELECT MAX(id) FROM locations_weather GROUP BY location)");
    List<Weather> latestWeather = queryResult.getResultList();
    return latestWeather;
  }

  /*
   * Endpoint to get average weather of specific location
   *  SELECT location, AVG(degree)
   *  FROM locations_weather
   *  WHERE location = :#{location}
   *  GROUP BY location;
   */

  // GET /weather/avg
  @GetMapping("/avg")
  public Weather avgWeather(@RequestParam(value = "location", defaultValue = "AlrawabiIoT") String location) {

    Query queryResult = em.createNativeQuery(
      "SELECT location, AVG(degree) FROM locations_weather GROUP BY location");
    Weather latestWeather = queryResult.getResult();
    return latestWeather;
  }

  // GET /weather/all
  @GetMapping("/all")
  public List<Weather> getWeathers() {

    return weatherConsumerService.getAllWeathers();
  }
}
