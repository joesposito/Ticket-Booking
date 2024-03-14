package com.trainer.ticketbooking.repo;

import com.trainer.ticketbooking.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepo extends CrudRepository<Ticket, Long> {
}
