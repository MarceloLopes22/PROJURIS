package com.desafio.projuris.projuris.basicas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Etapa {

	ABERTO(0, "Chamado Aberto."),
	ANDAMENTO(1, "Chamado em andamento."),
	PENDENTE(2, "Chamado pendente."),
	CONCLUIDO(3, "Chamado concluido.");
	
	private int chave;
	private String valor;
	
	public Etapa getEtapaBy(int chave) {
		for (Etapa etapa : Etapa.values()) {
			if (etapa.getChave() == chave) {
				return etapa;
			}
		}
		return null;
	}
	
	public Etapa getEtapaBy(String valor) {
		for (Etapa etapa : Etapa.values()) {
			if (etapa.getValor().equalsIgnoreCase(valor)) {
				return etapa;
			}
		}
		return null;
	}
}
