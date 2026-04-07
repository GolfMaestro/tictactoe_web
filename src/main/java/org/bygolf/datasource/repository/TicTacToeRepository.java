package org.bygolf.datasource.repository;

import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.domain.model.GameStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TicTacToeRepository extends CrudRepository<DSCurrentGame, Long> {

    DSCurrentGame findById(UUID id);

    List<DSCurrentGame> findByGameStatus(GameStatus status);

    @Query(value = "SELECT COUNT(*) FROM current_games WHERE host_id = :reqId OR member_id = :reqId", nativeQuery = true)
    int countUserGames(@Param("reqId") UUID id);

}
