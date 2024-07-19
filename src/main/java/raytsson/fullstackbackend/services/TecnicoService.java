package raytsson.fullstackbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raytsson.fullstackbackend.domain.Tecnico;
import raytsson.fullstackbackend.dto.TecnicoDTO;
import raytsson.fullstackbackend.repositories.TecnicoRepository;
import raytsson.fullstackbackend.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDto) {
        objDto.setId(null); // assegurar que o id vai vir null
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);
    }
}
