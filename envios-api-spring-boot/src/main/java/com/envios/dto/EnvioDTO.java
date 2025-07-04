package com.envios.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioDTO extends RepresentationModel<EnvioDTO> {
    private Long idVenta;
    private String direccionEnvio;
}
