package com.desafio.projuris.projuris.basicas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;

import com.desafio.projuris.projuris.basicas.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Equipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipamento_seq")
	@SequenceGenerator(name = "equipamento_seq", sequenceName = "equipamento_seq", allocationSize = 1)
	@JsonIgnore
	private Long id;
	
	@NotEmpty(message = "Por favor informar a descrição do equipamento.")
	private String descricaoEquipamento;
	
	private Tipo tipo;
	
	private String marca;
}
