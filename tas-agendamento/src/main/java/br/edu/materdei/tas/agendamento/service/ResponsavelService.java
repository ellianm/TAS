package br.edu.materdei.tas.agendamento.service;

import br.edu.materdei.tas.agendamento.entity.ResponsavelEntity;
import br.edu.materdei.tas.agendamento.repository.ResponsavelRepository;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ResponsavelService  implements IBaseService<ResponsavelEntity> {

    @Autowired
    private ResponsavelRepository repository;

    @Override
    @Transactional
    public List<ResponsavelEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public ResponsavelEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public ResponsavelEntity save(ResponsavelEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
}
