package com.ecommerce.demo.ticketingService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestData {
    @NotEmpty
    private String description;
    private String subject;
    private String email;
    private Number priority=2;
    private Number status =2;
   // private MultipartFile attachment=null;
    private String type;
}
