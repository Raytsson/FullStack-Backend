package raytsson.fullstackbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raytsson.fullstackbackend.domain.Chamado;
import raytsson.fullstackbackend.domain.Cliente;
import raytsson.fullstackbackend.domain.Tecnico;
import raytsson.fullstackbackend.domain.enums.Prioridade;
import raytsson.fullstackbackend.domain.enums.Status;
import raytsson.fullstackbackend.domain.dto.ChamadoDTO;
import raytsson.fullstackbackend.repositories.ChamadoRepository;
import raytsson.fullstackbackend.services.exceptions.ObjectNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;


    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll(){
        return chamadoRepository.findAll();
    }

    public Chamado create(ChamadoDTO objDto) {
        return chamadoRepository.save(newChamado(objDto));
    }

    public Chamado update(Integer id, ChamadoDTO objDto) {
        objDto.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDto);
        return chamadoRepository.save(oldObj);
    }

    private Chamado newChamado( ChamadoDTO obj){
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if (obj.getId() != null){
            chamado.setId(obj.getId());
        }
        if (obj.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }


}
