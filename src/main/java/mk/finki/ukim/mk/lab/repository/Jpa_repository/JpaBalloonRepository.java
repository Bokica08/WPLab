package mk.finki.ukim.mk.lab.repository.Jpa_repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaBalloonRepository  extends JpaRepository<Balloon,Long> {
    List<Balloon> findAllByNameOrDescription(String name,String description);
    void deleteByDescription(String desc);



}
