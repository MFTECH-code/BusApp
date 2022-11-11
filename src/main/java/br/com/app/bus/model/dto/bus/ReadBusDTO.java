package br.com.app.bus.model.dto.bus;

public record ReadBusDTO(
        Long id,
        String number,
        String line,
        String region,
        Double rate
) {
}
