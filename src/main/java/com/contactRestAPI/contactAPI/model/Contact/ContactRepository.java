package com.contactRestAPI.contactAPI.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Contact findById(int id);
    Contact findByName(String name);
}
