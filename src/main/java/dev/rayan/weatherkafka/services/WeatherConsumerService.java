package dev.rayan.weatherkafka.services;

import dev.rayan.weatherkafka.classes.Weather;
import dev.rayan.weatherkafka.repository.WeatherCRUD;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherConsumerService {

  ObjectMapper om = new ObjectMapper();

  @Autowired
  WeatherCRUD weatherCRUD;

  @KafkaListener(topics = "locations-weather", groupId = "groupId")
  public void listenToWeather(String weatherStr) {
    try {
      Weather weather = om.readValue(weatherStr, Weather.class);
      weatherCRUD.save(weather);

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  public List<Weather> getAllWeathers() {
    List<Weather> weathers = (List<Weather>) weatherCRUD.findAll();
    return weathers;
  }
}
