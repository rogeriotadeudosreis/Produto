package br.com.rogerio.produto.controller;

import br.com.rogerio.produto.model.Produto;
import br.com.rogerio.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/produto", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<Produto> salvar(@RequestBody @Valid Produto produto, UriComponentsBuilder uriBuilder) {

        if (produto.getId() == null) {
            Produto produtoSalvo = service.create(produto);
            URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
            return ResponseEntity.created(uri).body(produtoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        Optional<Produto> optional = service.findById(id);
        if (optional.isPresent()) {
            Produto produtoAtualizado = service.update(produto);
            return ResponseEntity.ok(produtoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> lista = service.findAll();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        Optional<Produto> optional = service.findById(id);
        if (optional.isPresent()) {
            service.delete(id);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> optional = service.findById(id);
        if (optional.isPresent()) {
            Produto produto = optional.get();
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar-por-nome/{nome}")
    public ResponseEntity<List<Produto>> listaPorNome(@PathVariable String nome) {
        List<Produto> lista = service.findByNome(nome);
        return ResponseEntity.ok().body(lista);
    }
}
