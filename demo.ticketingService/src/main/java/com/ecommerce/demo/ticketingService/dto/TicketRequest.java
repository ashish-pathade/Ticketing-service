package com.ecommerce.demo.ticketingService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    @NotEmpty(message = "please select type of ticket")
    private String ticketType;
    @NotEmpty
    @Size(min=5,max=150,message = "enter the character between 5 to 150")
    private String ticketDetails;
    @NotEmpty
    private String ticketSubject;
    @Id
    @Email
    private String emailId;
   // private MultipartFile file;
}
