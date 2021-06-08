package com.desafio.projuris.projuris.basicas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class Responsavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsavel_seq")
	@SequenceGenerator(name = "responsavel_seq", sequenceName = "responsavel_seq", allocationSize = 1)
	private Long id;
	
	@NotEmpty(message = "Por favor informar o nome do cliente.")
	private String nome;
	
}
