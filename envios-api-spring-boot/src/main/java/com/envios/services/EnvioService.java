package com.envios.services;
import java.util.List;
import com.envios.dto.EnvioDTO;

public interface EnvioService {
    EnvioDTO crearEnvio(EnvioDTO dto);
    EnvioDTO obtenerEnvio(Long id);
    EnvioDTO actualizarEstadoEnvio(Long id, String nuevoEstado);
    List<EnvioDTO> listarTodos();
}
