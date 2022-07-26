package dev.rayan.weatherkafka.controllers;

import dev.rayan.weatherkafka.classes.Weather;
import dev.rayan.weatherkafka.services.WeatherProducerService;
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

    producerWeather = weatherProducerService.createWeather(weather);
    weather = weatherConsumerService.listenToWeather(producerWeather);
    return weather;
  }

  // POST /weather/create
  @PostMapping(value = "/create")
  public Weather GeneratorFetcher() {
    Weather weather;
    return weather;
  }

  // GET /weather/all
  @GetMapping("/all")
  public List<Weather> getWeathers() {

    return weatherConsumerService.getAllWeathers();
  }
}
