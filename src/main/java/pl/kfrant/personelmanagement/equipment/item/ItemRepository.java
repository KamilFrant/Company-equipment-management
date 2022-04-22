package pl.kfrant.personelmanagement.equipment.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByNameContainsIgnoreCase(String name);
    Optional<Item> findBySerialNumber(String serialNumber);
}
