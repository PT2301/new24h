package com.news.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.news.entity.NewEntity;
@Repository
public interface NewRepository extends JpaRepository<NewEntity,Long>{
	Optional<NewEntity> findById(Long id);
	@Query(value = "SELECT * FROM new WHERE MATCH (title,shortdescription) AGAINST (?1) Limit ?2,?3", nativeQuery = true)
	List<NewEntity> getTotalSearch(String key,Integer offset,Integer limit);
	@Query(value = "SELECT * FROM new WHERE MATCH (title,shortdescription) AGAINST (?1)", nativeQuery = true)
	List<NewEntity>totalSearchNew(String key);
}
