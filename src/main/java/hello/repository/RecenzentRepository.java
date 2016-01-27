package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.model.Recenzent;

public interface RecenzentRepository extends JpaRepository<Recenzent, Long> {

}
