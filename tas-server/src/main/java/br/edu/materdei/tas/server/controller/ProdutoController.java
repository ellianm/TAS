package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.entity.ProdutoEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.ProdutoService;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping("produtos")
    public ResponseEntity<List<ProdutoService>> findAll() {
        List<ProdutoEntity> produtos = this.service.findAll();

        return new ResponseEntity(produtos, HttpStatus.OK);
    }
    @GetMapping("produtos/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        try {
            ProdutoEntity produto = this.service.findById(id);
            return new ResponseEntity(produto, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe um produto com esse código"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("produtos")
    public ResponseEntity create(@RequestBody ProdutoEntity produto) {
        try {

            this.service.save(produto);
            return new ResponseEntity(produto, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("produtos/{id}")
    public ResponseEntity update(@PathVariable("ID") Integer id, @RequestBody ProdutoEntity produto) {
        try {
            ProdutoEntity found = this.service.findById(id);
            produto.setId(found.getId());
            this.service.save(produto);
            return new ResponseEntity(produto, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe um produto com esse código"),HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("produtos/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            ProdutoEntity found = this.service.findById(id);
            this.service.delete(found.getId());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe um produto com esse código"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
