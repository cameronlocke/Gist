package com.thegist.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrictionRepository extends JpaSpecificationExecutor<Restriction>, JpaRepository<Restriction, Integer> {
}
