package com.ElectronicStore.ElectronicStore.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String categoryId;

    @NotBlank
    @Min(value = 4 ,message ="title must be of minimum 4 characters !!")
    private String title;

    @NotBlank(message = "Descrption required !!")
    private String descripton;

    private String coverImage;



}
