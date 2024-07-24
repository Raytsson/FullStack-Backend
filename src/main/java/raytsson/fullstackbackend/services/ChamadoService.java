package raytsson.fullstackbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raytsson.fullstackbackend.domain.Chamado;
import raytsson.fullstackbackend.repositories.ChamadoRepository;
import raytsson.fullstackbackend.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + id));
    }
}
