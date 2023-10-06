package com.stock.controllers;

import com.stock.domain.Operation;
import com.stock.dto.request.OperationRequest;
import com.stock.dto.response.OperationResponse;
import com.stock.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/operation")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Operation operation = operationService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new OperationResponse(operation));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Operation> allOperation = operationService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allOperation.stream().map(OperationResponse::new).toList());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OperationRequest operationRequest, UriComponentsBuilder uriBuilder) {
        Operation operation = operationService.create(operationRequest);
        URI uri = uriBuilder.path("/operation").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(new OperationResponse(operation));
    }
}
