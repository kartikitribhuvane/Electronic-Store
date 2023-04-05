package com.ElectronicStore.ElectronicStore.controllers;

import com.ElectronicStore.ElectronicStore.dtos.ApiResponseMessage;
import com.ElectronicStore.ElectronicStore.dtos.CategoryDto;
import com.ElectronicStore.ElectronicStore.dtos.PageableResponse;
import com.ElectronicStore.ElectronicStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // create
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        // call service to save object
        CategoryDto categoryDto1 = categoryService.create(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }
    // update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
        @PathVariable String categoryId,
        @RequestBody CategoryDto categoryDto
    ){
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // delete
     @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage> deleteCategory(
            @PathVariable String categoryId
     ){
        categoryService.delete(categoryId);
         ApiResponseMessage responseMessage= ApiResponseMessage.builder().message("Category is Deleted Successfully !!").status(HttpStatus.OK).success(true).build();
         return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


    // getall
@GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAll(
            @RequestParam(value ="pageNumber", defaultValue= "0", required= false) int pageNumber,
            @RequestParam(value ="pageSize", defaultValue= "10",required= false) int pagesize,
            @RequestParam(value ="sortBy", defaultValue= "title", required= false) String sortBy,
            @RequestParam(value ="sortDir", defaultValue= "asc", required= false) String sortDir
    ){

       PageableResponse<CategoryDto> pageableResponse= categoryService.getAll(pageNumber, pagesize, sortBy, sortDir);
       return new ResponseEntity<>(pageableResponse,HttpStatus.OK);

    }

    // get single
    @GetMapping("/{categoryId}")
   public ResponseEntity<CategoryDto> getSingle(@PathVariable String categoryId){
         CategoryDto categoryDto = categoryService.get(categoryId);
        return ResponseEntity.ok(categoryDto);
   }

}
