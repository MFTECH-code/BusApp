package br.com.app.bus.service;

import br.com.app.bus.model.dto.bus.CreateBusDTO;
import br.com.app.bus.model.dto.bus.ReadBusDTO;
import br.com.app.bus.model.dto.bus.UpdateBusDTO;
import br.com.app.bus.model.entity.Bus;
import br.com.app.bus.model.mapper.BusMapper;
import br.com.app.bus.model.repository.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    private final BusRepository repository;


    public BusService(BusRepository repository) {
        this.repository = repository;
    }

    public boolean create(Bus bus) {
        var hasError = bus.validateData();
        if (hasError)
            return true;

        repository.save(bus);
        return false;
    }

    public List<ReadBusDTO> getAll() {
        return repository.findAll().stream().map(BusMapper::readBus).toList();
    }

    public ReadBusDTO getByNumber(String number) throws Exception {
        var bus = repository.findByNumber(number).orElseThrow(() -> new Exception("Bus not found"));
        return BusMapper.readBus(bus);
    }

    public Bus getById(Long id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new Exception("Bus not found"));
    }

    public List<ReadBusDTO> getByLine(String line) throws Exception {
        var bus = repository.findByLine(line).orElseThrow(() -> new Exception("Bus not found"));
        return bus.stream().map(BusMapper::readBus).toList();
    }

    public void update(CreateBusDTO busDTO, Long id) throws Exception {
        var bus = findBus(id);
        bus.setRate(busDTO.rate());
        bus.setNumber(busDTO.number());
        bus.setRate(busDTO.rate());
        bus.setLine(busDTO.line());
        repository.save(bus);
    }

    public void delete(Long id) throws Exception {
        var bus = findBus(id);
        repository.delete(bus);
    }

    private Bus findBus(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Bus not found"));
    }

}
