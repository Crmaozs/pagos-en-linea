package com.pagos.co.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pagos.co.service.ClienteService;
import com.pagos.co.service.dto.ClienteDTO;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    List<ClienteDTO> clientes= new ArrayList<ClienteDTO>();
    Long id = 1L;
    
	@Override
	public ClienteDTO save(ClienteDTO clienteDTO) {
		
		System.out.println("Cliente save ----->>>>>: "+clienteDTO);
		log.debug("Request to save Cliente : {}", clienteDTO);
		
		Optional <ClienteDTO> oCliente = clientes.stream().filter(client -> client.getIdentification() == clienteDTO.getIdentification()).findFirst();
		
		if(oCliente.isPresent()){
			return oCliente.get();
		}
		clienteDTO.setId(this.id++);
		clientes.add(clienteDTO);
		return clienteDTO;
	}
	@Override
	public ClienteDTO getClienteByIdentification(Integer identification) {
		
		Optional <ClienteDTO> oCliente = clientes.stream().filter(client -> client.getIdentification() == identification).findFirst();
	
		if(oCliente.isPresent()){
			return oCliente.get();
		}
		return null;
	}
	
}