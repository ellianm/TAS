package br.edu.materdei.tas.agendamento.repository;

import br.edu.materdei.tas.agendamento.entity.AgendamentoPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoPedidoRepository extends JpaRepository<AgendamentoPedidoEntity, Integer> {
}
