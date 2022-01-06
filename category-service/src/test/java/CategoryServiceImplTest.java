import com.dionisius.finalproject.categoryservice.api.dto.input.CategoryInput;
import com.dionisius.finalproject.categoryservice.api.dto.output.CategoryOutput;
import com.dionisius.finalproject.categoryservice.data.model.Category;
import com.dionisius.finalproject.categoryservice.impl.repository.CategoryRepository;
import com.dionisius.finalproject.categoryservice.impl.service.CategoryServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    private final ModelMapper modelMapper = new ModelMapper();
    private Integer id;

    @InjectMocks // Somehow we need this, maybe because of autowired
    private CategoryServiceImpl service;
    @Mock
    private CategoryRepository repository;
    @Spy
    private ModelMapper mapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        id = EASY_RANDOM.nextInt();
    }

    @Test
    public void getAll_WillReturnListCategory() {
        Iterable<Category> categoryIterable = EASY_RANDOM.objects(Category.class, 2)
                .collect(Collectors.toList());
        when(repository.findAll()).thenReturn(categoryIterable);

        // When
        var result = service.getAll();

        // Then
        List<CategoryOutput> outputs = new ArrayList<>();
        for (Category category: categoryIterable) {
            outputs.add(modelMapper.map(category, CategoryOutput.class));
        }
        verify(repository, times(1)).findAll();
        assertEquals(outputs, result);
    }
    @Test
    public void getOne_WillReturnCategoryOuput() {
        // Given
        Category category = EASY_RANDOM.nextObject(Category.class);

        when(repository.findById(category.getId())).thenReturn(Optional.of(category));

        // When
        var result = service.getOne(category.getId());

        // Then
        verify(repository, times(1)).findById(category.getId());
        CategoryOutput output = modelMapper.map(category, CategoryOutput.class);
        assertEquals(output, result);
    }

    @Test
    public void update_WillReturnException() {
        // Given
        CategoryInput input = EASY_RANDOM.nextObject(CategoryInput.class);
        Category category = modelMapper.map(input, Category.class);
        String errMsg = "Not Found";
        when(repository.findById(id)).thenAnswer( invocation -> { throw new Exception(errMsg); });

        // When
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.update(id, input),
                "Not Found"
        );

        // Then
        assertTrue(thrown.getMessage().equals(errMsg));
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void delete_WillReturnException() {
        // Given
        doNothing().when(repository).deleteById(id);

        // When
        service.delete(id);

        // Then
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void addOne_WillDoNothing() {
        // Given
        CategoryInput input = EASY_RANDOM.nextObject(CategoryInput.class);
        Category category = modelMapper.map(input, Category.class);
        doNothing().when(repository).save(category);

        // When
        service.addOne(input);

        // Then
        verify(repository, times(1)).save(category);
    }


}
