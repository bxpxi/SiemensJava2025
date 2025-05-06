package com.siemens.internship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testSaveAndFind() {
        Item item = new Item(null, "RepoTest", "Description", "NEW", "repo@example.com");
        itemRepository.save(item);

        List<Item> items = itemRepository.findAll();
        assertFalse(items.isEmpty());
        assertEquals("RepoTest", items.get(0).getName());
    }

    @Test
    public void testFindAllIds() {
        Item item1 = new Item(null, "A", "Desc1", "NEW", "a@example.com");
        Item item2 = new Item(null, "B", "Desc2", "NEW", "b@example.com");
        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Long> ids = itemRepository.findAllIds();
        assertEquals(2, ids.size());
    }
}
