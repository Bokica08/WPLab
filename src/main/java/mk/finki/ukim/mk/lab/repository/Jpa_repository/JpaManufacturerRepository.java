package mk.finki.ukim.mk.lab.repository.Jpa_repository;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaManufacturerRepository extends JpaRepository<Manufacturer,Long> {
}
