package com.ElectronicStore.ElectronicStore.service;

import com.ElectronicStore.ElectronicStore.dtos.CategoryDto;
import com.ElectronicStore.ElectronicStore.dtos.PageableResponse;

public interface CategoryService {

    // create

    CategoryDto create(CategoryDto categoryDto);

    // update

    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //delete

    void delete(String categoryId);

    // get all

    PageableResponse<CategoryDto> getAll(int pageNumber, int pagesize, String sortBy, String sortDir);

    //get single category detail

    CategoryDto get(String categoryId);

    // search


}
