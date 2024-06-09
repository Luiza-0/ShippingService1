package com.shipment.ShippingService.controller;

import com.shipment.ShippingService.model.Shipment;
import com.shipment.ShippingService.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Long id) {
        return shipmentService.getShipmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Shipment createShipment(@RequestBody Shipment shipment) {
        return shipmentService.createShipment(shipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable Long id, @RequestBody Shipment shipmentDetails) {
        return shipmentService.updateShipment(id, shipmentDetails) != null
                ? ResponseEntity.ok(shipmentDetails)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }
}

