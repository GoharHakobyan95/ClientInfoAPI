package ru.unibell.clientinfoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.unibell.clientinfoapi.models.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
