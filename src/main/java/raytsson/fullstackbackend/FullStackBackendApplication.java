package raytsson.fullstackbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import raytsson.fullstackbackend.domain.Chamado;
import raytsson.fullstackbackend.domain.Cliente;
import raytsson.fullstackbackend.domain.Tecnico;
import raytsson.fullstackbackend.domain.enums.Prioridade;
import raytsson.fullstackbackend.domain.enums.Status;
import raytsson.fullstackbackend.repositories.ChamadoRepository;
import raytsson.fullstackbackend.repositories.ClienteRepository;
import raytsson.fullstackbackend.repositories.TecnicoRepository;

import java.util.Arrays;

@SpringBootApplication
public class FullStackBackendApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(FullStackBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico t1 = new Tecnico(null, "Valdir", "38277036892", "valdir@email.com", "123");

		Cliente c1 =  new Cliente(null, "teste do teste", "27244634803", "teste@email.com", "1234");

		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", t1, c1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
	}
}
