package pl.kfrant.personelmanagement.equipment.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kfrant.personelmanagement.equipment.category.dto.CategoryDto;

import java.util.List;

@Api(tags = "Categories")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    @ApiOperation("Take all categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.findAllCategoies());
    }

    @GetMapping("/names")
    @ApiOperation("Take all category names")
    public ResponseEntity<List<String>> getAllCategoryNames(){
        return ResponseEntity.ok(categoryService.findAllCategoryNames());
    }
}
