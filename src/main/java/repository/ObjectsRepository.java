package repository;

import entity.Objects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectsRepository extends JpaRepository<Objects, Integer> {

}