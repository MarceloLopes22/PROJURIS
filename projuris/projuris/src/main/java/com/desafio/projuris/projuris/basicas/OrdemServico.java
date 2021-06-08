package com.desafio.projuris.projuris.basicas;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.desafio.projuris.projuris.basicas.enums.Etapa;

import lombok.Data;

@Data
@Entity
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDate dataInicioAtendimento;
	
	private LocalDate dataFimAtendimento;
	
	@NotEmpty(message = "Por favor informar o procedimento executado.")
	private String detalhesExecutados;
	
	@NotEmpty(message = "Por favor informar o responsável da execução do serviço")
	private String responsavelExecucao;
	
	private Etapa etapa;
	
	@OneToMany
	private Cliente cliente;
	
}
