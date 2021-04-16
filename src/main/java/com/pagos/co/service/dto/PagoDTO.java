package com.pagos.co.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class PagoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
    
	private Double precio;

    private ZonedDateTime horaPedido;

    private StatusDTO status;

    private Long clientId;

    private Integer ref;
    
    private Double domicilio;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	

	/*
	 * @Override public boolean equals(Object o) { if (this == o) { return true; }
	 * if (!(o instanceof PagoDTO)) { return false; }
	 * 
	 * PagoDTO pagoDTO = (PagoDTO) o; if (this.id == null) { return false; } return
	 * Objects.equals(this.id, pagoDTO.id); }
	 */

	/*
	 * @Override public int hashCode() { return Objects.hash(this.id); }
	 */

}