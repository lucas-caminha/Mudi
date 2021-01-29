package br.com.caminha.mvc.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.caminha.mvc.mudi.model.Pedido;
import br.com.caminha.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Cacheable("books")
	public List<Pedido> findAll();
	
	public List<Pedido> findByStatus(StatusPedido status, Pageable sort);

	@Query("SELECT p FROM Pedido p join p.user u where u.username = :username")
	public List<Pedido> findAllByUsuario(@Param("username") String username);
	
	@Query("SELECT p FROM Pedido p join p.user u where u.username = :username and p.status = :status")
	public List<Pedido> findByStatusAndUser(@Param("status") StatusPedido status, @Param("username") String username);
}
