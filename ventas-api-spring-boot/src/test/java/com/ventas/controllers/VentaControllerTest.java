package com.ventas.controllers;

import com.ventas.dto.VentaDTO;
import com.ventas.services.VentaService;
import com.ventas.services.VentaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VentaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VentaService ventaService;

    @InjectMocks
    private VentaController ventaController;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks
        MockitoAnnotations.openMocks(this);
        // Configura MockMvc para usar el controlador
        mockMvc = MockMvcBuilders.standaloneSetup(ventaController).build();
    }

    // Test para crear una venta
    @Test
    void testCrearVenta() throws Exception {
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setIdVenta(1L);
        ventaDTO.setIdCliente(123);
        ventaDTO.setIdVendedor(456);
        ventaDTO.setTotal(new BigDecimal("100.00"));

        when(ventaService.crearVenta(any(VentaDTO.class))).thenReturn(ventaDTO);

        mockMvc.perform(post("/api/ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idCliente\": 123, \"idVendedor\": 456, \"total\": 100.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idVenta").value(1L))
                .andExpect(jsonPath("$.idCliente").value(123));

        verify(ventaService, times(1)).crearVenta(any(VentaDTO.class));
    }

    // Test para obtener una venta por ID
    @Test
    void testObtenerVenta() throws Exception {
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setIdVenta(1L);
        ventaDTO.setIdCliente(123);
        ventaDTO.setIdVendedor(456);
        ventaDTO.setTotal(new BigDecimal("100.00"));

        when(ventaService.obtenerVenta(1L)).thenReturn(ventaDTO);

        mockMvc.perform(get("/api/ventas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idVenta").value(1L))
                .andExpect(jsonPath("$.idCliente").value(123));

        verify(ventaService, times(1)).obtenerVenta(1L);
    }

    // Test para listar ventas por cliente
    @Test
    void testVentasPorCliente() throws Exception {
        VentaDTO ventaDTO1 = new VentaDTO(1L, 123, 456, new Date(), new BigDecimal("100.00"), null);
        VentaDTO ventaDTO2 = new VentaDTO(2L, 123, 456, new Date(), new BigDecimal("200.00"), null);
        List<VentaDTO> ventas = List.of(ventaDTO1, ventaDTO2);

        when(ventaService.obtenerVentasPorCliente(123)).thenReturn(ventas);

        mockMvc.perform(get("/api/ventas/cliente/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].idVenta").value(1L));

        verify(ventaService, times(1)).obtenerVentasPorCliente(123);
    }

    // Test para validar cupón
    @Test
    void testValidarCupon() throws Exception {
        when(ventaService.validarCupon(any())).thenReturn(true);

        mockMvc.perform(post("/api/ventas/cupones/validar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"codigo\": \"DISCOUNT10\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        verify(ventaService, times(1)).validarCupon(any());
    }

    // Test para obtener una venta con HATEOAS
    @Test
    void testObtenerVentaHateoas() throws Exception {
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setIdVenta(1L);
        ventaDTO.setIdCliente(123);

        when(ventaService.obtenerVenta(1L)).thenReturn(ventaDTO);

        mockMvc.perform(get("/api/ventas/hateoas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idVenta").value(1L))
                .andExpect(jsonPath("$.idCliente").value(123));

        verify(ventaService, times(1)).obtenerVenta(1L);
    }
}
