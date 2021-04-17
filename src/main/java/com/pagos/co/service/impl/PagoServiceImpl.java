package com.pagos.co.service.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pagos.co.domain.enumeration.PaymentStatus;
import com.pagos.co.service.ClienteService;
import com.pagos.co.service.PagoService;
import com.pagos.co.service.dto.ClienteDTO;
import com.pagos.co.service.dto.PagoDTO;
import com.pagos.co.service.dto.PedidoDTO;
import com.pagos.co.service.dto.StatusDTO;

@Service
public class PagoServiceImpl implements PagoService {

	private List<PagoDTO> pagos = new ArrayList<PagoDTO>();

	private PagoDTO pagoAcancelar = new PagoDTO();

	private PagoDTO pagoActualizar = new PagoDTO();

	private Long id = 0L;

	private final double domilicio = 7500;

	private final double iva = 0.19;

	private final ClienteService clienteService;

	public PagoServiceImpl(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public PagoDTO generate(PedidoDTO pedidoDTO) {

		ClienteDTO cliente = clienteService.getClienteByIdentification(pedidoDTO.getClienteDTO().getIdentification());
		Long clientId;
		if (cliente != null) {
			clientId = cliente.getId();
			
		} else {
			clientId = clienteService.save(pedidoDTO.getClienteDTO()).getId();
			
		}

		PagoDTO pagoDTO = new PagoDTO();

		if (pedidoDTO.getValue() > 70000 && pedidoDTO.getValue() <= 100000) {

			pagoDTO.setId(this.id++);
			pagoDTO.setPrecio(pedidoDTO.getValue() + this.domilicio + (this.iva * pedidoDTO.getValue()));
			pagoDTO.setClientId(clientId);
			pagoDTO.setHoraPedido(ZonedDateTime.now());
			pagoDTO.setStatus(new StatusDTO(PaymentStatus.FINISHED, "exitosa"));
			pagoDTO.setRef(2344321);
			pagoDTO.setDomicilio(domilicio);
		} else if (pedidoDTO.getValue() > 100000) {
			pagoDTO.setId(this.id++);
			pagoDTO.setPrecio(pedidoDTO.getValue() + (this.iva * pedidoDTO.getValue()));
			pagoDTO.setClientId(pedidoDTO.getClienteDTO().getId());
			pagoDTO.setHoraPedido(ZonedDateTime.now());
			pagoDTO.setStatus(new StatusDTO(PaymentStatus.FINISHED, "exitosa"));
			pagoDTO.setRef(2344321);
			pagoDTO.setDomicilio(0.0);
		} else {

			pagoDTO.setStatus(new StatusDTO(PaymentStatus.FAILED, "compra minima debe ser superior a $70.000"));
			
		}
		pagos.add(pagoDTO);
		return pagoDTO;
	}

	@Override
	public PagoDTO remove(PagoDTO p) {

		if (p.getId() == null) {
			pagoAcancelar.setStatus(new StatusDTO(PaymentStatus.FAILED, "No hay pagos disponibles"));
			return pagoAcancelar;
		}

		if (!pagos.isEmpty()) {
			pagos.forEach(pago -> {

				ZonedDateTime timeNow = ZonedDateTime.now();
				ZonedDateTime timeLimit = pago.getHoraPedido().plusMinutes(1);
				boolean isBeforeHour = timeNow.isBefore(timeLimit);

				if (isBeforeHour || pago.getId().equals(p.getId())) {
					pago.setStatus(new StatusDTO(PaymentStatus.CANCELED, "Pedido ha sido cancelado sin costo alguno"));
					pagoAcancelar = pago;
				} else if (!isBeforeHour || pago.getId().equals(p.getId())) {
					Double comision10 = 0.1 * pago.getPrecio();
					pagoAcancelar = pago;
					pagoAcancelar.setPrecio(comision10);
					pagoAcancelar.setStatus(new StatusDTO(PaymentStatus.CANCELED,
							"Usted debe pagar el 10% del valor del pedido que es: " + comision10));
				}
			});
			pagos.remove(pagoAcancelar);
		} else {
			pagoAcancelar.setStatus(new StatusDTO(PaymentStatus.FAILED, "No hay pagos disponibles"));

		}
		return pagoAcancelar;
	}
 
	public PagoDTO getPagoById(Long id) {

		Optional<PagoDTO> oPago = pagos.stream().filter(pago -> pago.getId() == id).findFirst();

		if (oPago.isPresent()) {
			return oPago.get();
		}
		return null;
	}

	@Override
	public PagoDTO update(PagoDTO p) {
		

		if (p.getId().equals(null)) {
			pagoActualizar.setStatus(new StatusDTO(PaymentStatus.FAILED, "No hay pagos disponibles"));
			return pagoActualizar;
		}

		if (!pagos.isEmpty()) {
			pagos.forEach(pago -> {
				
				ZonedDateTime timeNow = ZonedDateTime.now();
				ZonedDateTime timeLimit = pago.getHoraPedido().plusMinutes(1);
				boolean isBeforeHour = timeNow.isBefore(timeLimit);

				if (isBeforeHour && pago.getId().equals(p.getId())) {
					if (p.getPrecio() >= pago.getPrecio() && p.getPrecio() <= 100000) {
						
						pago.setPrecio(p.getPrecio() + this.domilicio + (p.getPrecio() * this.iva));
						pago.setStatus(new StatusDTO(PaymentStatus.UPDATED, "Pedido ha sido actualizado"));
						pagoActualizar = pago;
					} else if (p.getPrecio() > 100000) {
						
						pago.setStatus(new StatusDTO(PaymentStatus.UPDATED,
								"Pedido ha sido actualizado y" + " no se cobrará domicilio"));
						pago.setPrecio(p.getPrecio() + this.domilicio + (p.getPrecio() * this.iva));
						pago.setDomicilio(0.0);
						pagoActualizar = pago; 
					}else if (p.getPrecio() < 70000) {
						
						pago.setStatus(new StatusDTO(PaymentStatus.FINISHED,
								"El valor del pedido no puede ser" + " menor a 70000"));
						pagoActualizar = pago;
					} 
					else if (p.getPrecio() < pago.getPrecio()) {
						
						pago.setStatus(new StatusDTO(PaymentStatus.FINISHED,
								"El valor del pedido no puede ser" + " menor al actual total"));
						pagoActualizar = pago;
					}
 
				} else if (!isBeforeHour && pago.getId().equals(p.getId())) {
					
					pagoActualizar.setStatus(new StatusDTO(PaymentStatus.FINISHED,
							"Ya no puede actualizar el pedido debido sobre pasaste el tiempo límite (5 horas)"));
				}
			});
			return pagoActualizar;
		} else {
			
			pagoActualizar.setStatus(new StatusDTO(PaymentStatus.FAILED, "No hay pagos disponibles"));
			return pagoActualizar;

		}
	}

}