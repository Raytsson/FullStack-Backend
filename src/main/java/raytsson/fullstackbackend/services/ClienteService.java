package raytsson.fullstackbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raytsson.fullstackbackend.domain.Pessoa;
import raytsson.fullstackbackend.domain.Cliente;
import raytsson.fullstackbackend.dto.ClienteDTO;
import raytsson.fullstackbackend.repositories.ClienteRepository;
import raytsson.fullstackbackend.repositories.PessoaRepository;
import raytsson.fullstackbackend.services.exceptions.DataIntegrityViolationException;
import raytsson.fullstackbackend.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDto) {
        objDto.setId(null); // assegurar que o id vai vir null
        validaPorCPfeEmail(objDto);
        Cliente newObj = new Cliente(objDto);
        return repository.save(newObj);
    }

    public Cliente update(Integer id, ClienteDTO objDto) {
        objDto.setId(id);
        Cliente oldObj = findById(id);
        validaPorCPfeEmail(objDto);
        oldObj = new Cliente(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaPorCPfeEmail(ClienteDTO objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("CPF ja cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("Email ja cadastrado no sistema!");
        }
    }



}
