package pl.kfrant.personelmanagement.equipment.item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kfrant.personelmanagement.equipment.item.dto.ItemAssignmentDto;
import pl.kfrant.personelmanagement.equipment.item.dto.ItemDto;

import java.net.URI;
import java.util.List;

@Api(tags = "Items")
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("")
    @ApiOperation("Take all items")
    public ResponseEntity<List<ItemDto>> getAllItems(@RequestParam(required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok(itemService.findByName(name));
        } else
            return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Take item by id")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id){
        return itemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("")
    @ApiOperation("Create new item")
    public ResponseEntity<ItemDto> saveItem(@RequestBody ItemDto dto){
        if(dto.getId()!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Zapisywany przedmiot nie może mieć ustawionego ID.");
        }
        ItemDto savedItem = itemService.saveItem(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedItem);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update item")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id,@RequestBody ItemDto dto){
        if(!id.equals(dto.getId())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Aktualizowany przedmiot ma złe ID");
        }
        ItemDto updatedItem = itemService.updateItem(dto);
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping("/{id}/assignments")
    @ApiOperation("Take all assignments for this item")
    public ResponseEntity<List<ItemAssignmentDto>> getItemAssignmets(@PathVariable Long id){
        return ResponseEntity.ok(itemService.getItemAssignment(id));
    }
}
