package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;
import mk.finki.ukim.mk.lab.repository.Jpa_repository.JpaBalloonRepository;
import mk.finki.ukim.mk.lab.repository.Jpa_repository.JpaManufacturerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final JpaBalloonRepository balloonRepository;
    private final JpaManufacturerRepository manufacturerRepository;
    public BalloonServiceImpl(JpaBalloonRepository balloonRepository, JpaManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescription(text,text);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer=this.manufacturerRepository.findById(manufacturerId).get();
        return Optional.of(balloonRepository.save(new Balloon(name, description, manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return balloonRepository.findById(id);
    }

    @Override
    public void deleteByStr(String string) {
        balloonRepository.deleteByDescription(string);
    }
}
