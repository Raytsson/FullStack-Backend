package raytsson.fullstackbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raytsson.fullstackbackend.domain.Chamado;

public interface ChamadoRepository extends JpaRepository <Chamado, Integer> {

    
}
