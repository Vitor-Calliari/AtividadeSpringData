package br.com.bpkedu.produtoJPA.controller;

import br.com.bpkedu.produtoJPA.domain.Produto;
import br.com.bpkedu.produtoJPA.projection.ProdutoResumo;
import br.com.bpkedu.produtoJPA.repository.ProdutoRepository;
import br.com.bpkedu.produtoJPA.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    @GetMapping
    public Page<Produto> listar(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @GetMapping("/disponiveis")
    public List<ProdutoResumo> listarDisponiveis() {
        return produtoRepository.listarProdutosDisponiveis();
    }

    @GetMapping("/estoque-baixo")
    public List<Produto> listarEstoqueBaixo() {
        return produtoRepository.findProdutosComEstoqueBaixo();
    }

    @PostMapping("/atualizar-estoques")
    public void atualizarEstoques(@RequestBody List<Produto> produtos) {
        produtoService.atualizarEstoqueEmLote(produtos);
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        existente.setDescricao(produto.getDescricao());
        existente.setPreco(produto.getPreco());
        existente.setEstoque(produto.getEstoque());
        existente.setCategoria(produto.getCategoria());
        return produtoRepository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }

    @GetMapping("/busca")
    public List<Produto> buscarPorDescricao(@RequestParam String descricao) {
        return produtoRepository.findByDescricaoContaining(descricao);
    }

    @GetMapping("/preco")
    public List<Produto> buscarPorPreco(@RequestParam Double min, @RequestParam Double max) {
        return produtoRepository.findByPrecoBetween(min, max);
    }
}
