package com.thegist.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRoadManagerConsentRepository extends JpaRepository<ApplicationRoadManagerConsent, Integer>, JpaSpecificationExecutor<ApplicationRoadManagerConsent> {
}
