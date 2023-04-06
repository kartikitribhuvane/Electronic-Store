package com.ElectronicStore.ElectronicStore.repositories;

import com.ElectronicStore.ElectronicStore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String>{



}
