package raytsson.fullstackbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raytsson.fullstackbackend.domain.Pessoa;

public interface PessoaRepository extends JpaRepository <Pessoa, Integer> {


}
