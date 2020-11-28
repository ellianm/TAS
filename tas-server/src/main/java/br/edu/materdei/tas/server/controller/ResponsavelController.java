package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.agendamento.entity.ResponsavelEntity;
import br.edu.materdei.tas.agendamento.service.ResponsavelService;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResponsavelController {
    @Autowired
    private ResponsavelService service;

    @GetMapping("responsaveis")
    public ResponseEntity<List<ResponsavelEntity>> findAll() {
        try {
            List<ResponsavelEntity> clientes = service.findAll();

            return new ResponseEntity(clientes, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("responsaveis")
    public ResponseEntity create(@RequestBody ResponsavelEntity cliente) {
        try {
            this.service.save(cliente);

            return new ResponseEntity(cliente, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("responsaveis/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {

            ResponsavelEntity cliente = this.service.findById(id);

            return new ResponseEntity(cliente, HttpStatus.OK);

        } catch (ResourceNotFoundException e) {

            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um responsável com este código"),
                    HttpStatus.NOT_FOUND);

        } catch (Exception e) {

            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("responsaveis/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody ResponsavelEntity cliente) {
        try {
            ResponsavelEntity found = this.service.findById(id);
            cliente.setId(found.getId());
            this.service.save(cliente);
            return new ResponseEntity(cliente, HttpStatus.OK);

        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um responsável com este código"),
                    HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("responsaveis/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            ResponsavelEntity found = this.service.findById(id);

            this.service.delete(found.getId());

            return new ResponseEntity(HttpStatus.NO_CONTENT);

        } catch (ResourceNotFoundException e) {

            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um responsável com este código"),
                    HttpStatus.NOT_FOUND);

        } catch (Exception e) {

            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
