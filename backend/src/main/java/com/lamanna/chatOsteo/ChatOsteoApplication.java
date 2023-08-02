package com.lamanna.chatOsteo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.lamanna.chatOsteo.entities.Message;
import com.lamanna.chatOsteo.entities.Room;
import com.lamanna.chatOsteo.repositories.MessageRepository;
import com.lamanna.chatOsteo.repositories.RoomRepository;

@SpringBootApplication
public class ChatOsteoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatOsteoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RoomRepository repositoryRoom, MessageRepository repositoryMessage) {
		return (args) -> {
			// save a few customers
			Instant instant = Instant.now();
			ZoneId zoneId = ZoneId.systemDefault();

			// ofInstant(Instant instant, ZoneId zone)
			LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zoneId);

			Room room = repositoryRoom.save(new Room(1L, dateTime, "https://i.pravatar.cc/315"));

			Message message = new Message(1L, "Jack", room, 1L);

			repositoryMessage.save(message);

		};
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8100"));
		corsConfiguration.setAllowedMethods(Arrays.asList(CorsConfiguration.ALL));
		corsConfiguration.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
		source.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(source);
	}

}
