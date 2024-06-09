package com.shipment.ShippingService.service;

import com.shipment.ShippingService.dto.ShipmentNotificationDTO;
import com.shipment.ShippingService.model.Shipment;
import com.shipment.ShippingService.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    
    @Autowired
    private ShipmentRepository shipmentRepository;

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Optional<Shipment> getShipmentById(Long id) {
        return shipmentRepository.findById(id);
    }

    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }

    public void notifyGateway(Shipment shipment) {
        RestTemplate restTemplate = new RestTemplate();
        ShipmentNotificationDTO notification = new ShipmentNotificationDTO();
        notification.setShipmentId(shipment.getId());
        notification.setStatus(shipment.getStatus());
        notification.setTrackingNumber(shipment.getRastreamento());

        restTemplate.postForObject("http://api-gateway/api/notifications", notification, Void.class);
    }

    public Shipment createShipment(Shipment shipment) {
        Shipment savedShipment = shipmentRepository.save(shipment);
        notifyGateway(savedShipment);
        return savedShipment;
    }

    public Shipment updateShipment(Long id, Shipment shipmentDetails) {
        Optional<Shipment> optionalShipment = shipmentRepository.findById(id);
        if (optionalShipment.isPresent()) {
            Shipment shipment = optionalShipment.get();
            shipment.setTransportadora(shipmentDetails.getTransportadora());
            shipment.setStatus(shipmentDetails.getStatus());
            shipment.setRastreamento(shipmentDetails.getRastreamento());
            Shipment updatedShipment = shipmentRepository.save(shipment);
            notifyGateway(updatedShipment);
            return updatedShipment;
        }
        return null;
    }
}
