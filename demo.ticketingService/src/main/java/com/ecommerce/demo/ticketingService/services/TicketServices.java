package com.ecommerce.demo.ticketingService.services;

import com.ecommerce.demo.ticketingService.dto.TicketRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TicketServices {
    public void addTicket(TicketRequest ticketRequest);
    public List<TicketRequest> getAllTickets();
    public ResponseEntity<?> createTicketWithFile(MultipartFile file,TicketRequest ticketRequest);
}
