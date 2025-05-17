package br.com.bpkedu.produtoJPA.service;


import br.com.bpkedu.produtoJPA.domain.Pedido;
import br.com.bpkedu.produtoJPA.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido) {
        double total = pedido.getItens().stream()
                .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade())
                .sum();
        pedido.setTotal(total);
        pedido.setData(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }
}

