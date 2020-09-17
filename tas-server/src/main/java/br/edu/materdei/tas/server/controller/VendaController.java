package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.ProdutoService;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.venda.entity.VendaEntity;
import br.edu.materdei.tas.venda.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class VendaController {
    @Autowired
    private VendaService service;

    @GetMapping("vendas")
    public ResponseEntity<List<ProdutoService>> findAll() {
        List<VendaEntity> vendas = this.service.findAll();

        return new ResponseEntity(vendas, HttpStatus.OK);
    }
    @GetMapping("produtos/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        try {
            VendaEntity venda = this.service.findById(id);
            return new ResponseEntity(venda, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe uma venda com esse código"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("produtos")
    public ResponseEntity create(@RequestBody VendaEntity venda) {
        try {
            this.service.save(venda);
            return new ResponseEntity(venda, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("produtos/{id}")
    public ResponseEntity update(@PathVariable("ID") Integer id, @RequestBody VendaEntity venda) {
        try {
            VendaEntity found = this.service.findById(id);
            venda.setId(found.getId());
            this.service.save(venda);
            return new ResponseEntity(venda, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe uma venda com esse código"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("produtos/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            VendaEntity found = this.service.findById(id);
            this.service.delete(found.getId());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe uma venda com esse código"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
