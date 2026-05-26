package dc.unifacef.bd.repository;

import dc.unifacef.bd.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends
        JpaRepository<Produto, Long> {

    // a interface terá disponível os seguintes métodos
    // findAll() -> select * from produto
    // findById(id) -> select * from produto where id = ...
    // save -> insert into produto ou update produto set ...
    // deleteById(id) -> delete from produto where id =  ...
}
