package com.castlers.service.repository;


import com.castlers.service.dao.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PlayerRepository extends ReactiveCrudRepository<Player, String> {

}
