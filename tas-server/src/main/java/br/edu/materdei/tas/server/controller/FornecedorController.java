package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.compra.entity.FornecedorEntity;
import br.edu.materdei.tas.compra.service.FornecedorService;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class FornecedorController {

    @Autowired
    private FornecedorService service;

    @GetMapping("fornecedores")
    public ResponseEntity<List<FornecedorEntity>> findAll() {
        List<FornecedorEntity> fornecedores = this.service.findAll();

        return new ResponseEntity(fornecedores, HttpStatus.OK);
    }
    @GetMapping("fornecedores/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        try {
            FornecedorEntity fornecedor = this.service.findById(id);
            return new ResponseEntity(fornecedor, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe uma fornecedor com esse código"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("fornecedores")
    public ResponseEntity create(@RequestBody FornecedorEntity fornecedor) {
        try {

            this.service.save(fornecedor);
            return new ResponseEntity(fornecedor, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("fornecedores/{id}")
    public ResponseEntity update(@PathVariable("ID") Integer id, @RequestBody FornecedorEntity fornecedor) {
        try {
            FornecedorEntity found = this.service.findById(id);
            fornecedor.setId(found.getId());

            this.service.save(fornecedor);

            return new ResponseEntity(fornecedor, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe um fornecedor com esse código"),HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("fornecedors/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            FornecedorEntity found = this.service.findById(id);

            this.service.delete(found.getId());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe um fornecedor com esse código"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
