package com.nguyen1207.vishbe.repositories;

import com.nguyen1207.vishbe.entities.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
