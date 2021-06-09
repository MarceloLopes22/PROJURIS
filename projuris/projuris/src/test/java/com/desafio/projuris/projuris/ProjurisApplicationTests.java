package com.desafio.projuris.projuris;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;

import com.desafio.projuris.projuris.basicas.Cliente;
import com.desafio.projuris.projuris.basicas.Equipamento;
import com.desafio.projuris.projuris.basicas.OrdemServico;
import com.desafio.projuris.projuris.basicas.Responsavel;
import com.desafio.projuris.projuris.basicas.enums.Etapa;
import com.desafio.projuris.projuris.basicas.enums.Tipo;
import com.desafio.projuris.projuris.controllers.response.Response;
import com.desafio.projuris.projuris.repositorys.OrdemServicoRepository;
import com.desafio.projuris.projuris.services.OrdemServicoService;

@RunWith(MockitoJUnitRunner.class)
class ProjurisApplicationTests {
	
	@Mock
	private OrdemServicoRepository repository;
	
	@InjectMocks
	private OrdemServicoService service;
	
	public ProjurisApplicationTests() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSalvarSucesso() {
		ResponseEntity<?> salvar = service.salvar(mock(OrdemServico.class), mock(BeanPropertyBindingResult.class));
		Response<?> response = Response.class.cast(salvar.getBody());
		assertEquals(HttpStatus.CREATED, response.getHttpStatus());
	}
	
	@Test
	void testConsultarOrdemPendentePorResponsavelSucesso() {
		Response<?> responseDto = new Response<>();
		responseDto.setData(new OrdemServico());
		responseDto.setHttpStatus(HttpStatus.OK);
		responseDto.setMensagemSucesso("Ordem consultada com sucesso.");
		
		doReturn(new ArrayList<OrdemServico>()).when(repository).consultarOrdemPorEtapaPendenteEResponsavelPorNome("Roberto");
		ResponseEntity<?> salvar = service.consultarOrdemPendentePorResponsavel("Roberto");
		Response<?> response = Response.class.cast(salvar.getBody());
		assertNotNull(response.getMensagemSucesso());
	}
	
	@Test
	void testResponsavelCriarOrdemServicoSucesso() {
		
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setCliente(new Cliente(null, "Lopes", "Rua teste 1", "81988888888", "teste@gmail.com", new Equipamento(null, "descrição equipamento", Tipo.DOMESTICO, "qualquer")));
		ordemServico.setDetalhesExecutados("teste detalhes");
		ordemServico.setEtapa(Etapa.ABERTO);
		ordemServico.setResponsavel(new Responsavel(null, "Marcelo", LocalDate.now(), LocalDate.now()));
		
		OrdemServico ordemInserida = ordemServico;
		ordemInserida.setId(1L);
		ordemInserida.getCliente().setId(1L);
		ordemInserida.getCliente().getEquipamento().setId(1L);
		ordemInserida.getResponsavel().setId(1L);
		
		doReturn(ordemInserida).when(repository).save(ordemServico);
		
		ResponseEntity<?> salvar = service.responsavelCriarOrdemServico(ordemServico, new BeanPropertyBindingResult(null, null));
		Response<?> response = Response.class.cast(salvar.getBody());
		assertEquals(HttpStatus.CREATED, response.getHttpStatus());
	}

}
