package br.edu.materdei.tas.agendamento.repository;

import br.edu.materdei.tas.agendamento.entity.ResponsavelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<ResponsavelEntity, Integer> {
}
