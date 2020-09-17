package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.venda.entity.PedidoEntity;
import br.edu.materdei.tas.venda.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PedidoController {
    @Autowired
    private PedidoService service;

    @GetMapping("pedidos")
    public ResponseEntity<List<PedidoEntity>> findAll() {
        List<PedidoEntity> pedidos = this.service.findAll();

        return new ResponseEntity(pedidos, HttpStatus.OK);
    }
    @GetMapping("pedidos/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        try {
            PedidoEntity pedido = this.service.findById(id);
            return new ResponseEntity(pedido, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe um pedido com esse código"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("pedidos")
    public ResponseEntity create(@RequestBody PedidoEntity pedido) {
        try {

            this.service.save(pedido);
            return new ResponseEntity(pedido, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("pedidos/{id}")
    public ResponseEntity update(@PathVariable("ID") Integer id, @RequestBody PedidoEntity pedido) {
        try {
            PedidoEntity found = this.service.findById(id);
            pedido.setId(found.getId());

            this.service.save(pedido);

            return new ResponseEntity(pedido, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe um pedido com esse código"),HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("pedidos/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            PedidoEntity found = this.service.findById(id);

            this.service.delete(found.getId());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Não existe um pedido com esse código"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
