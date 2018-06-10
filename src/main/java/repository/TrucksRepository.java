package repository;

import entity.Trucks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrucksRepository extends JpaRepository<Trucks, Integer>{

}