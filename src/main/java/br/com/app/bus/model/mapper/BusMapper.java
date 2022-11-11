package br.com.app.bus.model.mapper;

import br.com.app.bus.model.dto.bus.CreateBusDTO;
import br.com.app.bus.model.dto.bus.ReadBusDTO;
import br.com.app.bus.model.entity.Bus;

public class BusMapper {
    public static Bus createBus(CreateBusDTO busDTO) {
        return new Bus(
                null,
                busDTO.number(),
                busDTO.line(),
                busDTO.region(),
                busDTO.rate()
        );
    }

    public static ReadBusDTO readBus(Bus bus) {
        return new ReadBusDTO(
                bus.getId(),
                bus.getNumber(),
                bus.getLine(),
                bus.getRegion(),
                bus.getRate()
        );
    }
}
