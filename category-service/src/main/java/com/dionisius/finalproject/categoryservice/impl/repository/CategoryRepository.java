package com.dionisius.finalproject.categoryservice.impl.repository;

import com.dionisius.finalproject.categoryservice.data.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Integer> {
}
