package com.system.domain.repository;


import java.util.List;

import com.system.domain.models.Contact;
import com.system.domain.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, String>  {
    @Query("select c from Contact c where  c.name = :name")
    Contact findByName(@Param("name") String name);
    List<Contact> findByUser(User user);
}
