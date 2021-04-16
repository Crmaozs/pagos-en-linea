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

	private Integer id = 0;

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
			System.out.println("clientId cliente != null: " + clientId);
		} else {
			clientId = clienteService.save(pedidoDTO.getClienteDTO()).getId();
			System.out.println("clientId null: " + clientId);
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
			// System.out.println("");
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

	@Override
	public PagoDTO getPagoById(Integer id) {

		Optional<PagoDTO> oPago = pagos.stream().filter(pago -> pago.getId() == id).findFirst();

		if (oPago.isPresent()) {
			return oPago.get();
		}
		return null;
	}

}