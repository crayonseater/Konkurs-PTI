package hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Korespondencja;

public interface KorespondencjaRepository extends JpaRepository<Korespondencja, Long> {
	
	public List<Korespondencja> findByPracaId(Long id);
	
	public List<Korespondencja> findByRecenzentId(Long id);
	
	public List<Korespondencja> findByPracaIsNull();
}
