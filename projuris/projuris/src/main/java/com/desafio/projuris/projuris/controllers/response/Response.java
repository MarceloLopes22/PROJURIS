package com.desafio.projuris.projuris.controllers.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Response<T> {

	private Object data;

	private List<String> erros = new ArrayList<>();
	
	private HttpStatus httpStatus;
	
	private String mensagemSucesso;

}