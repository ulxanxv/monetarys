package ru.monetarys.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.monetarys.dao.models.Credential;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    Optional<Credential> findByName(String name);

}
