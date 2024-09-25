package br.com.gavazzoni.bairroseguro.service;

import br.com.gavazzoni.bairroseguro.model.Crime;
import br.com.gavazzoni.bairroseguro.repository.CrimeRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CrimeService {
    private final CrimeRepository crimeRepository;

    @Autowired
    public CrimeService(CrimeRepository crimeRepository) {
        this.crimeRepository = crimeRepository;
    }

    public List<Crime> findAll() {
        return crimeRepository.findAll();
    }

    public void save(Crime crime) {
        crimeRepository.save(crime);
    }

    public List<String> getCities() {
        return crimeRepository.findDistinctCities();
    }

    public List<String> getEnvironments() {
        return crimeRepository.findDistinctEnvironment();
    }

    public List<Integer> getYears() {
        return crimeRepository.findDistinctYear();
    }

    public List<String> getNatures() {
        return crimeRepository.findDistinctNature();
    }

    public List<String> getTypes() {
        return crimeRepository.findDistinctNatureType();
    }

    public List<Crime> getPaginated(Integer page, Integer size, Optional<String> city, Optional<String> nature, Optional<String> environment, Optional<Integer> year, Optional<String> type) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Specification<Crime> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            city.ifPresent(c -> predicates.add(criteriaBuilder.equal(root.get("city"), c)));
            nature.ifPresent(n -> predicates.add(criteriaBuilder.equal(root.get("nature"), n)));
            environment.ifPresent(e -> predicates.add(criteriaBuilder.equal(root.get("environment"), e)));
            year.ifPresent(y -> predicates.add(criteriaBuilder.equal(root.get("year"), y)));
            type.ifPresent(t -> predicates.add(criteriaBuilder.equal(root.get("type"), t)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<Crime> crimePage = crimeRepository.findAll(spec, pageRequest);
        return crimePage.getContent();
    }
}