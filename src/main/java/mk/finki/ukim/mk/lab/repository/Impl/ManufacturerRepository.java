package mk.finki.ukim.mk.lab.repository.Impl;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {
    private List<Manufacturer> manufacturers;

    public ManufacturerRepository() {
        manufacturers=new ArrayList<>(5);
        manufacturers.add(new Manufacturer("nike","usa","123"));
        manufacturers.add(new Manufacturer("addidas","usa","5553333"));
        manufacturers.add(new Manufacturer("new balance","uk","gq"));
        manufacturers.add(new Manufacturer("ford","usa","qwe"));
        manufacturers.add(new Manufacturer("BMW","de","hihi"));
    }

    public List<Manufacturer> findAll()
    {
        return manufacturers;
    }
    public Optional<Manufacturer> findById(Long id)
    {
        return manufacturers.stream().filter(i->i.getId().equals(id)).findFirst();
    }
}
