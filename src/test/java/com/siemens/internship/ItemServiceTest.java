package com.siemens.internship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Item mockItem = new Item(1L, "Test", "Desc", "NEW", "test@example.com");
        when(itemRepository.findById(1L)).thenReturn(Optional.of(mockItem));

        Optional<Item> result = itemService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Test", result.get().getName());
    }

    @Test
    void testSaveItem() {
        Item item = new Item(null, "New", "Desc", "NEW", "new@example.com");
        when(itemRepository.save(item)).thenReturn(item);

        Item saved = itemService.save(item);
        assertEquals("New", saved.getName());
    }

    @Test
    void testDeleteById() {
        itemService.deleteById(1L);
        verify(itemRepository, times(1)).deleteById(1L);
    }
}
