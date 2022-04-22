package pl.kfrant.personelmanagement.equipment.category.mapper;

import org.springframework.stereotype.Component;
import pl.kfrant.personelmanagement.equipment.category.Category;
import pl.kfrant.personelmanagement.equipment.category.dto.CategoryDto;

@Component
public class CategoryMapper {

    public Category mapToCategory(CategoryDto dto){
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    public CategoryDto mapToCategoryDto(Category category){
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}
