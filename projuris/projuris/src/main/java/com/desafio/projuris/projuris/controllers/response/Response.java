package com.desafio.projuris.projuris.controllers.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Response<T> {

	private Object data;

	private List<String> erros;
	
	private HttpStatus httpStatus;
	
	private String mensagemSucesso;

}