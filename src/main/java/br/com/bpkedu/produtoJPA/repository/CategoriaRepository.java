package br.com.bpkedu.produtoJPA.repository;

import br.com.bpkedu.produtoJPA.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
