package raytsson.fullstackbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raytsson.fullstackbackend.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository <Tecnico, Integer> {

    
}
