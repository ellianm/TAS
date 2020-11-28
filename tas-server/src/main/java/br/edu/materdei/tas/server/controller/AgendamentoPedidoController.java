package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.agendamento.entity.AgendamentoPedidoEntity;
import br.edu.materdei.tas.agendamento.service.AgendamentoPedidoService;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgendamentoPedidoController {

    @Autowired
    private AgendamentoPedidoService service;

    @GetMapping("agendamentosPedidos")
    public ResponseEntity findAll() {
        try {
            List<AgendamentoPedidoEntity> agendamentos = service.findAll();

            return new ResponseEntity(agendamentos, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("agendamentosPedidos")
    public ResponseEntity create(@RequestBody AgendamentoPedidoEntity agendamento) {
        try {
            this.service.save(agendamento);

            return new ResponseEntity(agendamento, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("agendamentosPedidos/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            AgendamentoPedidoEntity agendamento = this.service.findById(id);
            return new ResponseEntity(agendamento, HttpStatus.OK);

        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um agendamento com este código"),
                    HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("agendamentosPedidos/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody AgendamentoPedidoEntity agendamento) {
        try {
            AgendamentoPedidoEntity found = this.service.findById(id);

            agendamento.setId(found.getId());

            this.service.save(agendamento);
            return new ResponseEntity(agendamento, HttpStatus.OK);

        } catch (ResourceNotFoundException e) {

            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um agendamento com este código"),
                    HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("agendamentosPedidos/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            AgendamentoPedidoEntity found = this.service.findById(id);
            this.service.delete(found.getId());
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        } catch (ResourceNotFoundException e) {

            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um agendamento com este código"),
                    HttpStatus.NOT_FOUND);

        } catch (Exception e) {

            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
