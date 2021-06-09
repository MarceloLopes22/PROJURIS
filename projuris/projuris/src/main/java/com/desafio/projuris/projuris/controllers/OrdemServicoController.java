package com.desafio.projuris.projuris.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.projuris.projuris.basicas.OrdemServico;
import com.desafio.projuris.projuris.services.OrdemServicoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrdemServicoController {
	
	@Autowired
	private OrdemServicoService service;

	/** Método résponsavel em salvar uma ordem de serviço.
	 * @param ordemServico
	 * @param result
	 * @return {@link ResponseEntity}
	 * */
	@PostMapping(value = "/ordem-servico/salvar")
	public ResponseEntity<?> salvar(@Valid @RequestBody OrdemServico ordemServico, BindingResult result) {
		return this.service.salvar(ordemServico, result);
	}
	
	/** Método résponsavel em consultar ordem de serviço pendente ligada ao responsavel.
	 * @param nome
	 * @return {@link ResponseEntity}
	 * */
	@RequestMapping(value = "/ordem-servico/consultar-ordem-pendente-por-responsavel/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarOrdemPendentePorResponsavel(@PathVariable("nome") String nome) {
		return this.service.consultarOrdemPendentePorResponsavel(nome);
	}
	
	/** Método résponsavel em salvar uma ordem de serviço com as data de inicio e fim do atendimento definidas pelo operador.
	 * @param nome
	 * @return {@link ResponseEntity}
	 * */
	@PostMapping(value = "/ordem-servico/responsavel-criar-ordem-servico")
	public ResponseEntity<?> responsavelCriarOrdemServico(@Valid @RequestBody OrdemServico ordemServico, BindingResult result) {
		return this.service.responsavelCriarOrdemServico(ordemServico, result);
	}
	
	/** Método résponsavel listar todas as ordens.
	 * @return {@link ResponseEntity}
	 * */
	@RequestMapping(value = "/ordem-servico/listar-todas-ordens", method = RequestMethod.GET)
	public ResponseEntity<?> listarTodasOrdens() {
		return this.service.listarTodasOrdens();
	}
}
