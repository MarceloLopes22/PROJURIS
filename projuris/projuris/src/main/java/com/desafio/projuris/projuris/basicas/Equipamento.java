package com.desafio.projuris.projuris.basicas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.desafio.projuris.projuris.basicas.enums.Tipo;

import lombok.Data;

@Data
@Entity
public class Equipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Por favor informar a descrição do equipamento.")
	private String descricaoEquipamento;
	
	private Tipo tipo;
	
	private String marca;
}
