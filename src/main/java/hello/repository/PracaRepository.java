package hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Praca;

public interface PracaRepository extends JpaRepository<Praca, Long> {

	public List<Praca> findByRecenzentIsNull();

	public List<Praca> findByRecenzentIsNotNull();
}
