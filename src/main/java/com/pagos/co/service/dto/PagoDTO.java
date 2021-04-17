package com.pagos.co.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class PagoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
    
	private Double precio;

    private ZonedDateTime horaPedido;

    private StatusDTO status;

    private Long clientId;

    private Integer ref;
    
    private Double domicilio;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    public StatusDTO getStatus() {
		return status;
	}

	public void setStatus(StatusDTO status) {
		this.status = status;
	}

	public Double getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Double domicilio) {
		this.domicilio = domicilio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public ZonedDateTime getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(ZonedDateTime horaPedido) {
        this.horaPedido = horaPedido;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

	@Override
	public String toString() {
		return "PagoDTO [id=" + id + ", precio=" + precio + ", horaPedido=" + horaPedido + ", status=" + status
				+ ", clientId=" + clientId + ", ref=" + ref + ", domicilio=" + domicilio + "]";
	}

}