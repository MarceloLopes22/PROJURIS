package com.desafio.projuris.projuris.basicas;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Responsavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsavel_seq")
	@SequenceGenerator(name = "responsavel_seq", sequenceName = "responsavel_seq", allocationSize = 1)
	@JsonIgnore
	private Long id;
	
	@NotEmpty(message = "Por favor informar o nome do cliente.")
	private String nome;
	
	@Transient
	private LocalDate dataInicioAtendimento;
	
	@Transient
	private LocalDate dataFimAtendimento;
	
}
