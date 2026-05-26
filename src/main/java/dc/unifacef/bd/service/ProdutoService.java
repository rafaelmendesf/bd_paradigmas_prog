package dc.unifacef.bd.service;

import dc.unifacef.bd.model.Produto;
import dc.unifacef.bd.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    // essa classe vai usar o ProdutoRepository explorando a injeção
    // de dependência pelo construtor
    private ProdutoRepository repo;

    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }
    // recupera todos os produtos
    public List<Produto> listar(){
        return repo.findAll();
    }
    // recupera um produto por id
    public Optional<Produto> buscarPorId(Long id) {
        return repo.findById(id);
    }
    // inserir um produto
    public Produto salvar(Produto produto){
        return repo.save(produto); // vai inserir pois produto não tem id
    }
    // remove um produto por id
    public boolean remover(Long id){
        if(repo.existsById(id)){
            // produto existe no banco
            repo.deleteById(id);
            return true;
        }
        return false; // não removeu pois produto não existe
    }
    // atualiza um produto
    public Produto atualizar(long id, Produto atual){
        if(repo.existsById(id)){
            // associa id ao produto atualizado
            atual.setId(id);
            repo.save(atual); // vai atualizar pois atual tem id
        }
        return null;
    }
}