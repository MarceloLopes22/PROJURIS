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
	
	@Autowired
	private OrdemServicoRepository repository;
	
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
		response.setMensagemSucesso("Serviço criado com sucesso.");
		
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<?> consultarOrdemPendentePorResponsavel(String nome) {
		Response<OrdemServico> response = new Response<OrdemServico>();
		
		List<OrdemServico> ordemConsultada = repository.consultarOrdemPorEtapaPendenteEResponsavelPorNome(nome);
		
		if (ordemConsultada != null) {
			response.setData(ordemConsultada);
			response.setHttpStatus(HttpStatus.OK);
			response.setMensagemSucesso("Ordem consultada com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(response);
	}

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
		response.setMensagemSucesso("Ordem de serviço criado com sucesso.");
		
		return ResponseEntity.ok(response);
	}

}
