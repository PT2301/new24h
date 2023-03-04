package com.news.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.news.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long>{
       CategoryEntity findOneByCode(String code);
       Optional<CategoryEntity> findById(Long id);
}
