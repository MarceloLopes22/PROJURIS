package com.desafio.projuris.projuris.basicas;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Por favor informar o nome do cliente.")
	private String nome;
	
	private String endereco;
	
	@Pattern(regexp="(^$|[0-9]{10})", message = "Por favor informar um telefone válido.")
	private String telefone;
	
	@Email(message = "Por favor informar um email valido.", regexp = "^(.+)@(.+)$")
	private String email;

	@OneToMany
	private Set<Equipamento> equipamentos = new HashSet<>();
}
