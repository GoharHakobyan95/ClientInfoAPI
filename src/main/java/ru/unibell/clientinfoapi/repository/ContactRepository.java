package ru.unibell.clientinfoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.unibell.clientinfoapi.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    boolean existsByValue(String value);


}
