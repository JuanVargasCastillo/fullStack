package com.envios.services;

import com.envios.dto.EnvioDTO;

public interface EnvioService {
    EnvioDTO crearEnvio(EnvioDTO dto);
    EnvioDTO obtenerEnvio(Long id);
    EnvioDTO actualizarEstadoEnvio(Long id, String nuevoEstado);
}
