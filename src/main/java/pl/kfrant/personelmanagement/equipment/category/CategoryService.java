package pl.kfrant.personelmanagement.equipment.category;

import org.springframework.stereotype.Service;
import pl.kfrant.personelmanagement.equipment.category.dto.CategoryDto;
import pl.kfrant.personelmanagement.equipment.category.mapper.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    List<CategoryDto> findAllCategoies(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    List<String> findAllCategoryNames(){
        return categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }


}
