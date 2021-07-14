package com.system.domain.repository;


import com.system.domain.models.Contact;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(contactRepository).isNotNull();
    }



    @Test
    public void save() {
        Contact contact = new Contact();
        contact.setName("name");
        contact.setEmail("name@example.com");
        contact.setPhone("12345-6789");
        Contact found = this.contactRepository.save(contact);
        assertThat(found).isNotNull();
        assertEquals(contact.getName(),found.getName());

    }

    @Test
    public void updateContact_should_return_contact_with_data_updated() {
        Contact contact = new Contact();
        contact.setName("jhon");
        contact.setEmail("jhon@example.com");
        contact.setPhone("12345-6789");
        contactRepository.save(contact);
        Contact contact1 = contactRepository.findByName("jhon");
        contact.setPhone("1122-4455");
        contactRepository.save(contact);
        assertThat(contact1).isNotNull();
       assertNotEquals("12345-6789",contact1.getPhone());


    }
}
