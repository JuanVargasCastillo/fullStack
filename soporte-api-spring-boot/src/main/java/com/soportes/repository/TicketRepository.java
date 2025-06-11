package com.soportes.repository;

import com.soportes.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByIdUsuario(Integer idUsuario);
}
