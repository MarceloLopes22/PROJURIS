package com.desafio.projuris.projuris.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.desafio.projuris.projuris.basicas.OrdemServico;
import com.desafio.projuris.projuris.basicas.Responsavel;
import com.desafio.projuris.projuris.controllers.response.Response;
import com.desafio.projuris.projuris.repositorys.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	
	private static final String ORDEM_DE_SERVIÇO_CRIADO_COM_SUCESSO = "Ordem de serviço criado com sucesso.";
	
	private static final String ORDEM_CONSULTADA_COM_SUCESSO = "Ordem consultada com sucesso.";
	
	private static final String SERVIÇO_CRIADO_COM_SUCESSO = "Serviço criado com sucesso.";
	
	@Autowired
	private OrdemServicoRepository repository;
	
	/** Método résponsavel em salvar uma ordem de serviço.
	 * @param ordemServico
	 * @param result
	 * @return {@link ResponseEntity}
	 * */
	public ResponseEntity<?> salvar(@Valid OrdemServico ordemServico, BindingResult result) {
		Response<OrdemServico> response = new Response<OrdemServico>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(response);
		}
		
		OrdemServico ordem = repository.save(ordemServico);
		
		response.setData(ordem);
		response.setHttpStatus(HttpStatus.CREATED);
		response.setMensagemSucesso(SERVIÇO_CRIADO_COM_SUCESSO);
		
		return ResponseEntity.ok(response);
	}

	/** Método résponsavel em consultar ordem de serviço pendente ligada ao responsavel.
	 * @param nome
	 * @return {@link ResponseEntity}
	 * */
	public ResponseEntity<?> consultarOrdemPendentePorResponsavel(String nome) {
		Response<OrdemServico> response = new Response<OrdemServico>();
		
		List<OrdemServico> ordemConsultada = repository.consultarOrdemPorEtapaPendenteEResponsavelPorNome(nome);
		
		if (ordemConsultada != null) {
			response.setData(ordemConsultada);
			response.setHttpStatus(HttpStatus.OK);
			response.setMensagemSucesso(ORDEM_CONSULTADA_COM_SUCESSO);
		} else {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(response);
	}

	/** Método résponsavel em salvar uma ordem de serviço com as data de inicio e fim do atendimento definidas pelo operador.
	 * @param nome
	 * @return {@link ResponseEntity}
	 * */
	public ResponseEntity<?> responsavelCriarOrdemServico(@Valid OrdemServico ordemServico, BindingResult result) {
		Response<OrdemServico> response = new Response<OrdemServico>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(response);
		}
		
		Responsavel responsavel = ordemServico.getResponsavel();
		
		ordemServico.setDataInicioAtendimento(responsavel.getDataInicioAtendimento());
		ordemServico.setDataFimAtendimento(responsavel.getDataFimAtendimento());
		
		OrdemServico ordem = repository.save(ordemServico);
		
		response.setData(ordem);
		response.setHttpStatus(HttpStatus.CREATED);
		response.setMensagemSucesso(ORDEM_DE_SERVIÇO_CRIADO_COM_SUCESSO);
		
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<?> listarTodasOrdens() {
		List<OrdemServico> ordens = repository.findAll();
		
		if (ordens.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(ordens);
	}

}
