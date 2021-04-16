package com.pagos.co.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class PedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private ClienteDTO clienteDTO;

	private Double value;
	
	private ZonedDateTime horaPedido;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public ZonedDateTime getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(ZonedDateTime horaPedido) {
		this.horaPedido = horaPedido;
	}

}