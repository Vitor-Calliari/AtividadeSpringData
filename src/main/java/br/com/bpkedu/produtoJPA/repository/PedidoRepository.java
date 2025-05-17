package br.com.bpkedu.produtoJPA.repository;

import br.com.bpkedu.produtoJPA.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE p.data BETWEEN :inicio AND :fim AND p.total > 100")
    List<Pedido> findByDataAndTotal(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}

