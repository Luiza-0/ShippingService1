package com.shipment.ShippingService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Shipment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transportadora;
    private String status;
    private String rastreamento;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(String transportadora) {
		this.transportadora = transportadora;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRastreamento() {
		return rastreamento;
	}
	public void setRastreamento(String rastreamento) {
		this.rastreamento = rastreamento;
	}

    // Getters e Setters
}
