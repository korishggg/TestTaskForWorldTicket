package net.worldticket.data.repositories;

import net.worldticket.data.Sheep;
import net.worldticket.data.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheepRepository extends JpaRepository<Sheep, Long> {
    List<Sheep> findAllByState(State state);
}
