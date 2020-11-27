package br.edu.materdei.tas.agendamento.repository;

import br.edu.materdei.tas.agendamento.entity.AgendamentoCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoCompraRepository extends JpaRepository<AgendamentoCompraEntity, Integer> {
}
