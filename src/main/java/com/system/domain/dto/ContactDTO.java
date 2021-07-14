package com.system.domain.dto;

import com.system.domain.models.Contact;
import com.system.domain.models.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactDTO {
    private String id;
    private String name;
    private String phone;
    private String email;
    private User user;
    public static ContactDTO convertToRequest(Contact contact) {
        return new ContactDTO(contact.getId(), contact.getName(),contact.getPhone(),contact.getEmail(),contact.getUser());
    }
    public static List<ContactDTO> convertToRequest(List<Contact> contactList) {
       return  contactList.stream().map(ContactDTO::convertToRequest).
                collect(Collectors.toList());
    }
}
