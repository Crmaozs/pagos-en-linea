package com.pagos.co.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pagos.co.service.ClienteService;
import com.pagos.co.service.PagoService;
import com.pagos.co.service.dto.PedidoDTO;

import com.pagos.co.service.dto.PagoDTO;

@RestController
@RequestMapping("/api")
public class PagoController {

	private final Logger log = LoggerFactory.getLogger(PagoController.class);

	private final PagoService pagoService;

	public PagoController(ClienteService clienteService, PagoService pagoService) {
		this.pagoService = pagoService;
	}

	@PostMapping("/pago")
	@ResponseBody
	public ResponseEntity<PagoDTO> generatePago(@RequestBody PedidoDTO pedidoDTO) {
		log.info("REST request to see Pedido : {}", pedidoDTO);
		PagoDTO result = pagoService.generate(pedidoDTO);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/pedido/eliminar")
	@ResponseBody
	public ResponseEntity<PagoDTO> deletePedido(@RequestBody PagoDTO pago) {
		PagoDTO result = pagoService.remove(pago);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/pedido/editar")
	@ResponseBody
	public ResponseEntity<PagoDTO> editarPedido(@RequestBody PagoDTO pago) {
		PagoDTO result = pagoService.update(pago);
		return ResponseEntity.ok(result);
	}
}
