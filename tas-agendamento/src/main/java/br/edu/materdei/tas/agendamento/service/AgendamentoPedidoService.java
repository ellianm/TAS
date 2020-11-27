package br.edu.materdei.tas.agendamento.service;

import br.edu.materdei.tas.agendamento.repository.AgendamentoPedidoRepository;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.agendamento.entity.AgendamentoPedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AgendamentoPedidoService implements IBaseService<AgendamentoPedidoEntity> {

    @Autowired
    private AgendamentoPedidoRepository repository;

    @Override
    @Transactional
    public List<AgendamentoPedidoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public AgendamentoPedidoEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public AgendamentoPedidoEntity save(AgendamentoPedidoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }

}
