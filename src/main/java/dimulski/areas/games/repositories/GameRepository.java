package dimulski.areas.games.repositories;

import dimulski.areas.games.entities.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    @Query(value = "SELECT g FROM Game AS g")
    List<Game> findAll();
}
