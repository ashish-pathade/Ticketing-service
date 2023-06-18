package com.ecommerce.demo.ticketingService.controller;

import com.ecommerce.demo.ticketingService.dto.TicketRequest;
import com.ecommerce.demo.ticketingService.services.TicketServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TicketingController {
    @Autowired
    private TicketServices ticketServices;
    @Autowired
    ObjectMapper objectMapper;
    TicketRequest ticketRequest;
    @PostMapping("/tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTicket(@RequestBody TicketRequest ticketRequest){
        ticketServices.addTicket(ticketRequest);
    }
    @PostMapping("/file-tickets")
    public ResponseEntity<?> createTicketByForm(@RequestParam("file")MultipartFile file ,
                                   @RequestParam("ticketDetails") String ticketDetails){
        try {
           ticketRequest=objectMapper.readValue(ticketDetails,TicketRequest.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");
        }
       ticketServices.createTicketWithFile(file,ticketRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("ticket created");
    }
}
