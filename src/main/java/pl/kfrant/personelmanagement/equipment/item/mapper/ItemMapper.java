package pl.kfrant.personelmanagement.equipment.item.mapper;

import org.springframework.stereotype.Component;
import pl.kfrant.personelmanagement.equipment.category.Category;
import pl.kfrant.personelmanagement.equipment.category.CategoryRepository;
import pl.kfrant.personelmanagement.equipment.item.Item;
import pl.kfrant.personelmanagement.equipment.item.dto.ItemDto;

import java.util.Optional;

@Component
public class ItemMapper {

    private CategoryRepository categoryRepository;

    public ItemMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Item mapToItem(ItemDto dto) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setSerialNumber(dto.getSerialNumber());
        Optional<Category> category = categoryRepository.findByName(dto.getCategoryName());
        category.ifPresent(item::setCategory);
        return item;
    }

    public ItemDto mapToItemDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setSerialNumber(item.getSerialNumber());
        if (item.getCategory() != null)
            dto.setCategoryName(item.getCategory().getName());
        return dto;
    }
}
