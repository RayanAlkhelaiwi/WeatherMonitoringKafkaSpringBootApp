package dev.rayan.weatherkafka.controllers;

import dev.rayan.weatherkafka.classes.Weather;
import dev.rayan.weatherkafka.services.WeatherProducerService;
import dev.rayan.weatherkafka.services.WeatherConsumerService;
import java.util.List;
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

  /*
   * Endpoint to get average weather of specific location
   *  SELECT location, AVG(degree)
   *  FROM locations_weather
   *  GROUP BY location;
   */

  // GET /weather/all
  @GetMapping("/all")
  public List<Weather> getWeathers() {

    return weatherConsumerService.getAllWeathers();
  }
}
