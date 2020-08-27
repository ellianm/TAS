package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GrupoService implements IBaseService {

    @Autowired
    private GrupoRepository repository;

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public Object findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id);
    }

    @Override
    public Object save(Object entity) {
        return null;
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {

    }
}
