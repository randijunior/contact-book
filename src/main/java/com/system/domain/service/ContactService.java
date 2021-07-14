package com.system.domain.service;

import java.util.List;
import java.util.Optional;

import com.system.domain.dto.ContactDTO;
import com.system.domain.models.Contact;
import com.system.domain.models.User;
import com.system.domain.repository.ContactRepository;
import com.system.domain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    @Autowired
	private UserRepository userRepository;

    @Transactional
    public Contact create(ContactDTO request) {
        Contact contact = new Contact();
        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(userRepository.findByUsername(getCurrentUser()));
        return this.contactRepository.save(contact);
    }

    @Transactional
    public Contact update(ContactDTO request) {
        Contact contactoToUpdate = this.contactRepository.findById(request.getId()).get();
        contactoToUpdate.setName(request.getName());
        contactoToUpdate.setEmail(request.getEmail());
        contactoToUpdate.setPhone(request.getPhone());
        contactoToUpdate.setUser(userRepository.findByUsername(getCurrentUser()));
        return this.contactRepository.save(contactoToUpdate);
    }

    @Transactional
    public void delete(String id) {
        final Optional<Contact> contact =
            this.contactRepository.findById(id);
            contact.ifPresent(this.contactRepository::delete);
    }
    public List<Contact> findByUser() {
        User user = userRepository.findByUsername(getCurrentUser());
        return this.contactRepository.findByUser(user);
    }
    public Contact findOne(String name) {
       return this.contactRepository.findByName(name);
    }

    private String getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
if (principal instanceof UserDetails) {
  username = ((UserDetails)principal).getUsername();
} else {
  username = principal.toString();
}
return username;
    }

}
