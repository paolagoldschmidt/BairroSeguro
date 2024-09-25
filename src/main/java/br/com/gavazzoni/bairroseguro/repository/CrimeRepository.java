package br.com.gavazzoni.bairroseguro.repository;

import br.com.gavazzoni.bairroseguro.model.Crime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrimeRepository extends JpaRepository<Crime, Long>, JpaSpecificationExecutor<Crime> {
    @Query("SELECT DISTINCT c.municipality FROM Crime c")
    List<String> findDistinctCities();

    @Query("SELECT DISTINCT c.year FROM Crime c")
    List<Integer> findDistinctYear();

    @Query("SELECT DISTINCT c.environment FROM Crime c")
    List<String> findDistinctEnvironment();

    @Query("SELECT DISTINCT c.nature FROM Crime c")
    List<String> findDistinctNature();

    @Query("SELECT DISTINCT c.natureType FROM Crime c")
    List<String> findDistinctNatureType();
}