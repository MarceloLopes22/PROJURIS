package com.desafio.projuris.projuris.basicas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Tipo {

	ELETRONICO(0, "Eletronico"),
	DOMESTICO(1, "Domestico"),
	ELETRODOMESTICO(2, "Eletrodomestico"),
	OUTROS(3, "Outros");
	
	private int chave;
	private String valor;
	
	public Tipo getTipoBy(int chave) {
		for (Tipo tipo : Tipo.values()) {
			if (tipo.getChave() == chave) {
				return tipo;
			}
		}
		return null;
	}
	
	public Tipo getTipoBy(String valor) {
		for (Tipo tipo : Tipo.values()) {
			if (tipo.getValor().equalsIgnoreCase(valor)) {
				return tipo;
			}
		}
		return null;
	}
}
