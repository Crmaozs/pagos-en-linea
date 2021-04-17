package com.pagos.co.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.pagos.co.domain.enumeration.PaymentStatus;

public class Pago implements Serializable {

    
    private static final long serialVersionUID = 1L;

    private Double precio;

    private ZonedDateTime horaPedido;

    private PaymentStatus state;

    private Long clientId;

    private Integer ref;

    public Double getPrecio() {
        return this.precio;
    }

    public Pago precio(Double precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public ZonedDateTime getHoraPedido() {
        return this.horaPedido;
    }

    public Pago horaPedido(ZonedDateTime horaPedido) {
        this.horaPedido = horaPedido;
        return this;
    }

    public void setHoraPedido(ZonedDateTime horaPedido) {
        this.horaPedido = horaPedido;
    }

    public PaymentStatus getState() {
        return this.state;
    }

    public Pago state(PaymentStatus state) {
        this.state = state;
        return this;
    }

    public void setState(PaymentStatus state) {
        this.state = state;
    }

    public Long getClientId() {
        return this.clientId;
    }

    public Pago clientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Integer getRef() {
        return this.ref;
    }

    public Pago ref(Integer ref) {
        this.ref = ref;
        return this;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Pago{" +
            "  precio=" + getPrecio() +
            ", horaPedido='" + getHoraPedido() + "'" +
            ", state='" + getState() + "'" +
            ", clientId=" + getClientId() +
            ", ref=" + getRef() +
            "}";
    }

}