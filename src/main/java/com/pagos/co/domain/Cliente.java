package com.pagos.co.domain;

import java.io.Serializable;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer identification;

    private String address;

    public Cliente(Long id, String name, Integer identification, String address) {
		super();
		this.id = id;
		this.name = name;
		this.identification = identification;
		this.address = address;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Cliente name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdentification() {
        return this.identification;
    }

    public Cliente identification(Integer identification) {
        this.identification = identification;
        return this;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }

    public String getAddress() {
        return this.address;
    }

    public Cliente address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cliente)) {
            return false;
        }
        return id != null && id.equals(((Cliente) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", identification=" + getIdentification() +
            ", address='" + getAddress() + "'" +
            "}";
    }
    
}
