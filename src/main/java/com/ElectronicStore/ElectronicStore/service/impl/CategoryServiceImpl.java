package com.ElectronicStore.ElectronicStore.service.impl;

import com.ElectronicStore.ElectronicStore.dtos.CategoryDto;
import com.ElectronicStore.ElectronicStore.dtos.PageableResponse;
import com.ElectronicStore.ElectronicStore.entities.Category;
import com.ElectronicStore.ElectronicStore.exceptions.ResourceNotFoundException;
import com.ElectronicStore.ElectronicStore.helper.Helper;
import com.ElectronicStore.ElectronicStore.repositories.CategoryRepo;
import com.ElectronicStore.ElectronicStore.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return mapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {

        // get category of given id
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found Exception !!"));

        // update  category details
        category.setTitle(categoryDto.getTitle());
        category.setDescripton(categoryDto.getDescripton());
        category.setCoverImage(categoryDto.getCoverImage());
         Category updateCategory = categoryRepo.save(category);
        return mapper.map(updateCategory, CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {

        // get category of given id
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found Exception !!"));
        categoryRepo.delete(category);
    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pagesize, String sortBy, String sortDir) {

        Sort sort =(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pagesize,sort);
        Page<Category> page = categoryRepo.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page,CategoryDto.class);

        return pageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found Exception !!"));
        return mapper.map(category, CategoryDto.class);
    }
}
