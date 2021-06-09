package com.desafio.projuris.projuris.basicas;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.desafio.projuris.projuris.basicas.enums.Etapa;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordemservico_seq")
	@SequenceGenerator(name = "ordemservico_seq", sequenceName = "ordemservico_seq", allocationSize = 1)
	@JsonIgnore
	private Long id;
	
	private LocalDate dataInicioAtendimento;
	
	private LocalDate dataFimAtendimento;
	
	@NotEmpty(message = "Por favor informar o procedimento executado.")
	private String detalhesExecutados;
	
	private Etapa etapa;
	
	@NotNull(message = "Por favor informar o responsável da execução do serviço")
	@OneToOne(cascade = CascadeType.ALL)
	private Responsavel responsavel;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cliente cliente;
	
}
