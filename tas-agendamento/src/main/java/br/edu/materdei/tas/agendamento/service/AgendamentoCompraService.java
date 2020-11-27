package br.edu.materdei.tas.agendamento.service;

import br.edu.materdei.tas.agendamento.repository.AgendamentoCompraRepository;
import br.edu.materdei.tas.agendamento.entity.AgendamentoCompraEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AgendamentoCompraService implements IBaseService<AgendamentoCompraEntity> {

    @Autowired
    private AgendamentoCompraRepository repository;

    @Override
    @Transactional
    public List<AgendamentoCompraEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public AgendamentoCompraEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public AgendamentoCompraEntity save(AgendamentoCompraEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }

}
