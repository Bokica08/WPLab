package mk.finki.ukim.mk.lab.repository.Impl;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {
    List<Balloon> balloonList;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonRepository(ManufacturerRepository manufacturerRepository)
    {
        this.manufacturerRepository = manufacturerRepository;
        balloonList=new ArrayList<>(10);
        for (int i=0;i<10;i++)
        {
            balloonList.add(new Balloon("Name "+i,"zdr",manufacturerRepository.findAll().get(i/2)));
        }
    }
    public Optional<Balloon> findById(Long id)
    {
        return balloonList.stream().filter(i->i.getId().equals(id)).findFirst();
    }
    public List<Balloon> findAllBalloons()
    {
        return balloonList;
    }
    public List<Balloon> findAllByNameOrDescription(String text)
    {
      return   balloonList.stream().filter(i->i.getName().equals(text) || i.getDescription().equals(text))
              .collect(Collectors.toList());
    }
    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer)
    {
        balloonList.removeIf(i->i.getName().equals(name));
        Balloon b=new Balloon(name,description,manufacturer);
        balloonList.add(b);
        return Optional.of(b);
    }
    public void deleteById(Long id)
    {
        balloonList.removeIf(i->i.getId().equals(id));
    }
    public void deleteByStr(String string)
    {
        balloonList.removeIf(i->i.getDescription().contains(string));
    }

}
