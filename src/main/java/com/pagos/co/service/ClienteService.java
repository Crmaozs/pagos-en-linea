package com.pagos.co.service;

import com.pagos.co.service.dto.ClienteDTO;

public interface ClienteService {

	ClienteDTO save(ClienteDTO clienteDTO);

	ClienteDTO getClienteByIdentification(Integer identification);
	
}
