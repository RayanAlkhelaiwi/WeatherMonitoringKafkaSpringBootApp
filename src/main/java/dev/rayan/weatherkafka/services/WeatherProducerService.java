package dev.rayan.weatherkafka.services;

import dev.rayan.weatherkafka.classes.Weather;
import dev.rayan.weatherkafka.repository.WeatherCRUD;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WeatherProducerService {

  @Autowired
  WeatherCRUD weatherCRUD;

  @Value("${kafka.topic.name}")
  private String topicName;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  ObjectMapper om = new ObjectMapper();

  public Weather createWeather (Weather weather) {
    weather = weatherCRUD.save(weather);

    // send message to the topic in kafka broker
    String message = null;
    try {

      message = om.writeValueAsString(weather);
    } catch (JsonProcessingException e) {

      e.printStackTrace();
    }

    kafkaTemplate.send(topicName, message);
    return weather;
  }

  public List<Weather> getAllWeathers() {
    List<Weather> weathers = (List<Weather>) weatherCRUD.findAll();
    return weathers;
  }
}
