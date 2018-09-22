package persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
