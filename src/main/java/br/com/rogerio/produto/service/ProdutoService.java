package br.com.rogerio.produto.service;

import br.com.rogerio.produto.model.Produto;
import br.com.rogerio.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto create(Produto produto) {
        return repository.save(produto);
    }

    public Produto update(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> findAll() {
        List<Produto> produtos = repository.findAll();
        return produtos;
    }

    public void delete(Long id) {
       repository.deleteById(id);
    }

    public Optional<Produto> findById(Long id) {
        Optional<Produto> produtoOptional = repository.findById(id);
        return produtoOptional;
    }

    public List<Produto> findByNome(String nome) {
        List<Produto> produtosName = repository.findByNome(nome);
        return produtosName;
    }

    public List<Produto> findByPreco(Double valor) {
        List<Produto> produtosPreco = repository.findByPreco(valor);
        return produtosPreco;
    }


}
