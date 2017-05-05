package dimulski.areas.games.repositories;

import dimulski.areas.games.entities.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    @Query(value = "SELECT g FROM Genre AS g")
    List<Genre> findAll();
}
