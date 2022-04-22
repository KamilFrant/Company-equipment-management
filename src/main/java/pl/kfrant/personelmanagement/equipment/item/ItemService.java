package pl.kfrant.personelmanagement.equipment.item;

import org.springframework.stereotype.Service;
import pl.kfrant.personelmanagement.equipment.item.dto.ItemDto;
import pl.kfrant.personelmanagement.equipment.item.mapper.ItemMapper;

import javax.management.openmbean.OpenDataException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    List<ItemDto> findAll() {
        return itemRepository.findAll()
                .stream()
                .map(itemMapper::mapToItemDto)
                .collect(Collectors.toList());
    }

    Optional<ItemDto> findById(Long id) {
        return itemRepository.findById(id)
                .map(itemMapper::mapToItemDto);
    }

    List<ItemDto> findByName(String name) {
        return itemRepository.findByNameContainsIgnoreCase(name)
                .stream()
                .map(itemMapper::mapToItemDto)
                .collect(Collectors.toList());
    }

    ItemDto saveItem(ItemDto dto) {
        Optional<Item> itemBySerialNo = itemRepository.findBySerialNumber(dto.getSerialNumber());
        itemBySerialNo.ifPresent(item -> {
            throw new DuplicateSerialNumberException();
        });
        return mapAndSave(dto);
    }

    ItemDto updateItem(ItemDto dto) {
        Optional<Item> itemBySerialNo = itemRepository.findBySerialNumber(dto.getSerialNumber());
        itemBySerialNo.ifPresent(item -> {
            if (!item.getId().equals(dto.getId()))
                throw new DuplicateSerialNumberException();
        });
        return mapAndSave(dto);
    }


    private ItemDto mapAndSave(ItemDto dto) {
        Item itemToSave = itemMapper.mapToItem(dto);
        Item savedItem = itemRepository.save(itemToSave);
        return itemMapper.mapToItemDto(savedItem);
    }
}
