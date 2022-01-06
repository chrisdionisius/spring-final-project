import com.dionisius.finalproject.logservice.model.Log;
import com.dionisius.finalproject.logservice.repository.LogRepository;
import com.dionisius.finalproject.logservice.service.LogServiceImpl;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LogServiceImplTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    private final ModelMapper modelMapper = new ModelMapper();
    private Long id;

    @InjectMocks // Somehow we need this, maybe because of autowired
    private LogServiceImpl service;
    @Mock
    private LogRepository repository;
    @Spy
    private ModelMapper mapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        id = EASY_RANDOM.nextObject(Long.class);
    }

    @Test
    public void listLog_WillReturnListLogs() {
        Iterable<Log> logIterable = EASY_RANDOM.objects(Log.class, 2)
                .collect(Collectors.toList());
        when(repository.findAll()).thenReturn((List<Log>) logIterable);

        // When
        var result = service.listLog();

        // Then
        List<Log> outputs = new ArrayList<>();
        for (Log log: logIterable) {
            outputs.add(modelMapper.map(log, Log.class));
        }
        verify(repository, times(1)).findAll();
        assertEquals(outputs, result);
    }

    @Test
    public void create_WillReturnLog() {
        // Given
        Log input = EASY_RANDOM.nextObject(Log.class);
        when(repository.save(input)).thenReturn(input);

        // When
        var result = service.create(input);

        // Then
        verify(repository, times(1)).save(input);
    }
}
