package com.envios.services;

import com.envios.dto.EnvioDTO;
import com.envios.models.Envio;
import com.envios.repository.EnvioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EnvioServiceImpl implements EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Override
    public EnvioDTO crearEnvio(EnvioDTO dto) {
        Envio envio = new Envio();
        envio.setIdVenta(dto.getIdVenta());
        envio.setDireccionEnvio(dto.getDireccionEnvio());
        envio.setEstadoEnvio("pendiente");
        envio.setFechaEnvio(new Date());

        envio = envioRepository.save(envio);

        dto.setIdVenta(envio.getIdVenta());
        return dto;
    }

    @Override
    public EnvioDTO obtenerEnvio(Long id) {
        Envio envio = envioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Envio no encontrado"));

        EnvioDTO dto = new EnvioDTO();
        dto.setIdVenta(envio.getIdVenta());
        dto.setDireccionEnvio(envio.getDireccionEnvio());
        return dto;
    }

    @Override
    public EnvioDTO actualizarEstadoEnvio(Long id, String nuevoEstado) {
        Envio envio = envioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Envio no encontrado"));

        envio.setEstadoEnvio(nuevoEstado);
        envio.setFechaEntrega(new Date()); // opcional, depende del estado
        envioRepository.save(envio);

        EnvioDTO dto = new EnvioDTO();
        dto.setIdVenta(envio.getIdVenta());
        dto.setDireccionEnvio(envio.getDireccionEnvio());
        return dto;
    }
}

