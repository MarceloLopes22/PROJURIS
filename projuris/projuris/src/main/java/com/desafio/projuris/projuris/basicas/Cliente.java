package com.desafio.projuris.projuris.basicas;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
	@SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", allocationSize = 1)
	@JsonIgnore
	private Long id;
	
	@NotEmpty(message = "Por favor informar o nome do cliente.")
	private String nome;
	
	private String endereco;

	@NotEmpty(message = "Por favor informe o telefone")
	private String telefone;
	
	@Email(message = "Por favor informar um email valido.", regexp = "^(.+)@(.+)$")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	private Equipamento equipamento;
}
