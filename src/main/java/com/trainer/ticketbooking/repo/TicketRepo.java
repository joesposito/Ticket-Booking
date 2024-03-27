package com.trainer.ticketbooking.repo;

import com.trainer.ticketbooking.entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface TicketRepo extends CrudRepository<Ticket, Long>, ListCrudRepository<Ticket, Long> {
}
