package com.ecommerce.demo.ticketingService.services;

import com.ecommerce.demo.ticketingService.config.Configurations;
import com.ecommerce.demo.ticketingService.dto.TicketRequest;
import com.ecommerce.demo.ticketingService.dto.TicketRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

@Service
public class TicketServiesImpl implements TicketServices {
    @Autowired
    private Configurations configuration;

    @Override
    public void addTicket(TicketRequest ticketRequest) {
        TicketRequestData ticketRequestData = TicketRequestData.builder()
                .description(ticketRequest.getTicketDetails())
                .subject(ticketRequest.getTicketSubject())
                .email(ticketRequest.getEmailId())
                .priority(2)
                .status(2)
                // .attachment(ticketRequest.getFile())
                .type(ticketRequest.getTicketType())
                .build();
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("myw8fkS5Hn0zrU78N8O", "X");
        try{
        ResponseEntity<String> response = configuration.restTemplate().postForEntity
                ("https://ticketingservicetest3.freshdesk.com/api/v2/tickets",
                new HttpEntity<>(ticketRequestData, headers), String.class);
        if(response.getStatusCode()== HttpStatus.CREATED){
            System.out.println(response.getBody());
        }else {
            System.out.println("something went wrong");
        }}catch (Exception e){
            System.out.println(HttpStatus.BAD_REQUEST);
        }
        }
    @Override
    public List<TicketRequest> getAllTickets() {
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("myw8fkS5Hn0zrU78N8O", "X");

        return null;
    }

    @Override
    public ResponseEntity<?> createTicketWithFile(MultipartFile file, TicketRequest ticketRequest) {

        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setBasicAuth("myw8fkS5Hn0zrU78N8O", "X");
        MultiValueMap<String,Object> request=new LinkedMultiValueMap<>();
        try {
            request.add("attachments[]", Arrays.asList(new ByteArrayResource(file.getBytes())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        request.add("email",ticketRequest.getEmailId());
        request.add("subject",ticketRequest.getTicketSubject());
        request.add("description",ticketRequest.getTicketDetails());
        request.add("priority",2);
        request.add("status",2);
        request.add("type",ticketRequest.getTicketType());
        ResponseEntity<String> response = configuration.restTemplate().postForEntity
                ("https://ticketingservicetest3.freshdesk.com/api/v2/tickets",
                        new HttpEntity<>(request, headers), String.class);
        System.out.println(response);

        return response;
    }
}
