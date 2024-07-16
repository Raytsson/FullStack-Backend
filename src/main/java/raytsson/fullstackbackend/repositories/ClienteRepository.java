package raytsson.fullstackbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raytsson.fullstackbackend.domain.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {

    
}
