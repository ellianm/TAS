package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.compra.entity.CompraEntity;
import br.edu.materdei.tas.compra.service.CompraService;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CompraController {
    @Autowired
    private CompraService service;

    @GetMapping("compras")
    public ResponseEntity<List<CompraEntity>> findAll() {
        List<CompraEntity> compras = this.service.findAll();

        return new ResponseEntity(compras, HttpStatus.OK);
    }
    @GetMapping("compras/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        try {
            CompraEntity compra = this.service.findById(id);
            return new ResponseEntity(compra, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe uma compra com esse código"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("compras")
    public ResponseEntity create(@RequestBody CompraEntity compra) {
        try {

            this.service.save(compra);
            return new ResponseEntity(compra, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("compras/{id}")
    public ResponseEntity update(@PathVariable("ID") Integer id, @RequestBody CompraEntity compra) {
        try {
            CompraEntity found = this.service.findById(id);
            compra.setId(found.getId());

            this.service.save(compra);

            return new ResponseEntity(compra, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe uma compra com esse código"),HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("compras/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            CompraEntity found = this.service.findById(id);

            this.service.delete(found.getId());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe uma compra com esse código"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
