package dev.rayan.weatherkafka.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.rayan.weatherkafka.classes.Greeting;

@RestController
public class GreetingController {

	private static final String template = "Hey, %s! 👋🏻";

	@GetMapping("/")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "you") String name) {
		return new Greeting(String.format(template, name));
	}
}
