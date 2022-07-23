package dev.rayan.weatherkafka.repository;

import dev.rayan.weatherkafka.classes.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherCRUD extends CrudRepository<Weather, Integer> {

}
