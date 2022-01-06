import com.dionisius.finalproject.logservice.model.Log;
import com.dionisius.finalproject.logservice.repository.LogRepository;
import com.dionisius.finalproject.logservice.service.KafkaLogImpl;
import com.dionisius.finalproject.logservice.service.LogServiceImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

public class KafkaLogImplTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    private final ModelMapper modelMapper = new ModelMapper();
    private Long id;

    @InjectMocks // Somehow we need this, maybe because of autowired
    private KafkaLogImpl service;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        id = EASY_RANDOM.nextObject(Long.class);
    }
}
