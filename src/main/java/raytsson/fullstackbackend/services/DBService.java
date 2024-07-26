package raytsson.fullstackbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import raytsson.fullstackbackend.domain.Chamado;
import raytsson.fullstackbackend.domain.Cliente;
import raytsson.fullstackbackend.domain.Tecnico;
import raytsson.fullstackbackend.domain.enums.Prioridade;
import raytsson.fullstackbackend.domain.enums.Status;
import raytsson.fullstackbackend.repositories.ChamadoRepository;
import raytsson.fullstackbackend.repositories.ClienteRepository;
import raytsson.fullstackbackend.repositories.TecnicoRepository;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB(){
        Tecnico t1 = new Tecnico(null, "Valdir", "38277036892", "valdir@email.com", encoder.encode("123"));

        Cliente c1 =  new Cliente(null, "teste do teste", "27244634803", "teste@email.com", encoder.encode("123"));

        Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", t1, c1);

        tecnicoRepository.saveAll(Arrays.asList(t1));
        clienteRepository.saveAll(Arrays.asList(c1));
        chamadoRepository.saveAll(Arrays.asList(ch1));
    }
}
