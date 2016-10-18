package com.thegist.domain;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer>, JpaSpecificationExecutor<Collection> {
	
	
	// Search for records with KEY/VALUE in hstore
	@Query(value = "SELECT c FROM collection c WHERE c.collection -> ':key' = :value", nativeQuery = true)
	List<Collection> findByAttributesKV(@Param("key") String key, @Param("value") String value);

	// Search for records with KEY in hstore
    @Query(value = "SELECT c FROM collection c WHERE c.collection ? ':key", nativeQuery = true) 
	List<Collection> findByAttributesK(@Param("key") String key);
	
	
	
}
