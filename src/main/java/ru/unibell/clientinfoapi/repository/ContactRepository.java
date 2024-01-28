package ru.unibell.clientinfoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.unibell.clientinfoapi.models.entity.Contact;
import ru.unibell.clientinfoapi.models.entity.EmailContact;
import ru.unibell.clientinfoapi.models.entity.PhoneContact;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    boolean existsByValue(String value);

    List<PhoneContact> findPhoneContactsByClientClientId(Long clientId);

    List<EmailContact> findEmailContactsByClientClientId(Long clientId);

}
