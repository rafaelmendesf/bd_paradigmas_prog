package dc.unifacef.bd.controller;

import dc.unifacef.bd.model.Produto;
import dc.unifacef.bd.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    // essa classe vai usar o ProdutoService explorando a injeção
    // de dependência pelo construtor
    ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar(){
        List<Produto> resposta = service.listar();
        if (!resposta.isEmpty()){
            return ResponseEntity.ok(resposta); // 200
        }
        return ResponseEntity.noContent().build(); // sem produto no banco - 204
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> buscarPorId(@PathVariable Long id){
        Optional<Produto> resposta = service.buscarPorId(id);
        if (resposta.isPresent()){
            return ResponseEntity.ok(resposta); // 200
        }
        return ResponseEntity.notFound().build(); // 404
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        // Insere produto no banco
        Produto novo = service.salvar(produto);
        // Cria uma uri (Uniform Resource Identifier) com o id do novo produto
        URI uri = URI.create("/produtos/" + novo.getId());
        // Devolve código 201 - created
        return ResponseEntity.created(uri).body(novo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        if(service.remover(id)){
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); // 404
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id,
                                             @RequestBody Produto atual){
        Produto resposta = service.atualizar(id,atual);
        if (resposta == null){
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(resposta); // 200
    }
}