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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

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
	void testSalvarErroBadRequest() {
		OrdemServico ordemServico = new OrdemServico();
		BindingResult result = new BeanPropertyBindingResult(ordemServico, "ordemServico");
		result.addError(new ObjectError("detalhesExecutados", "Por favor informar o procedimento executado."));
		ResponseEntity<?> salvar = service.salvar(ordemServico, result);
		Response<?> response = Response.class.cast(salvar.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
		assertEquals(result.getAllErrors().get(0).getDefaultMessage(), response.getErros().get(0));
	}
	
	@Test
	void testConsultarOrdemPendentePorResponsavelSucesso() {
		doReturn(new ArrayList<OrdemServico>()).when(repository).consultarOrdemPorEtapaPendenteEResponsavelPorNome("Roberto");
		ResponseEntity<?> salvar = service.consultarOrdemPendentePorResponsavel("Roberto");
		Response<?> response = Response.class.cast(salvar.getBody());
		assertNotNull(response.getMensagemSucesso());
	}
	
	@Test
	void testConsultarOrdemPendentePorResponsavelSemDado() {
		doReturn(null).when(repository).consultarOrdemPorEtapaPendenteEResponsavelPorNome("Roberto");
		ResponseEntity<?> response = service.consultarOrdemPendentePorResponsavel("Roberto");
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
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
	
	@Test
	void testResponsavelCriarOrdemServicoErroBadRequest() {
		
		OrdemServico ordemServico = new OrdemServico();
		BindingResult result = new BeanPropertyBindingResult(ordemServico, "ordemServico");
		result.addError(new ObjectError("responsavel", "Por favor informar o responsável da execução do serviço"));
		
		ResponseEntity<?> salvar = service.responsavelCriarOrdemServico(ordemServico, result);
		Response<?> response = Response.class.cast(salvar.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
		assertEquals(result.getAllErrors().get(0).getDefaultMessage(), response.getErros().get(0));
	}

}
