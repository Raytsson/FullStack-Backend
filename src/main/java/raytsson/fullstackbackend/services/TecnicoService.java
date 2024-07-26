package raytsson.fullstackbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import raytsson.fullstackbackend.domain.Pessoa;
import raytsson.fullstackbackend.domain.Tecnico;
import raytsson.fullstackbackend.domain.dto.TecnicoDTO;
import raytsson.fullstackbackend.repositories.PessoaRepository;
import raytsson.fullstackbackend.repositories.TecnicoRepository;
import raytsson.fullstackbackend.services.exceptions.DataIntegrityViolationException;
import raytsson.fullstackbackend.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDto) {
        objDto.setId(null); // assegurar que o id vai vir null
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        validaPorCPfeEmail(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);
    }

    public Tecnico update(Integer id, TecnicoDTO objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCPfeEmail(objDto);
        oldObj = new Tecnico(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Tecnico possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaPorCPfeEmail(TecnicoDTO objDto) {
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
