package pl.kfrant.personelmanagement.equipment.item;

import org.springframework.stereotype.Service;
import pl.kfrant.personelmanagement.equipment.item.dto.ItemAssignmentDto;
import pl.kfrant.personelmanagement.equipment.item.dto.ItemDto;
import pl.kfrant.personelmanagement.equipment.item.exception.DuplicateSerialNumberException;
import pl.kfrant.personelmanagement.equipment.item.exception.ItemNotFoundException;
import pl.kfrant.personelmanagement.equipment.item.mapper.ItemAssignmentMapper;
import pl.kfrant.personelmanagement.equipment.item.mapper.ItemMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ItemAssignmentMapper itemAssignmentMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper,ItemAssignmentMapper itemAssignmentMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.itemAssignmentMapper=itemAssignmentMapper;
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

    public List<ItemAssignmentDto> getItemAssignment(Long id) {
        return itemRepository.findById(id)
                .map(Item::getAssignments)
                .orElseThrow(ItemNotFoundException::new)
                .stream()
                .map(itemAssignmentMapper::assignmentToDto)
                .collect(Collectors.toList());
    }
}
