package com.castlers.service;

import com.castlers.service.dao.Player;
import com.castlers.service.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}

@Component
@Log4j2
@RequiredArgsConstructor
class TestInitializer {

    private final PlayerRepository playerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {
        var users = Flux
                .just("test", "admin", "user")
                .map(name -> new Player(null, name))
                .flatMap(playerRepository::save);

        playerRepository.deleteAll().thenMany(users).thenMany(playerRepository.findAll()).subscribe(log::info);
    }
}
