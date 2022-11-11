package br.com.app.bus.model.dto.bus;

import com.sun.istack.NotNull;

public record CreateBusDTO(
        @NotNull
        String number,
        @NotNull
        String line,
        @NotNull
        String region,
        @NotNull
        Double rate
) {
}
