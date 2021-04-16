package com.pagos.co.service;

import com.pagos.co.service.dto.PagoDTO;
import com.pagos.co.service.dto.PedidoDTO;

public interface PagoService {

	PagoDTO generate(PedidoDTO pedidoDTO);

	PagoDTO remove(PagoDTO pago);

	PagoDTO getPagoById(Integer id);
	
}
