package dk.kea.dat19c.spring_mvc_mock.service;

import dk.kea.dat19c.spring_mvc_mock.model.Animal;
import dk.kea.dat19c.spring_mvc_mock.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class AnimalServiceTest {

    private static final long ANIMAL_ID = 5;
    @InjectMocks
    AnimalService animalService; // Service to test
    @Mock
    AnimalRepository mockedAnimalRepository; // Mocked repository that will return testdata


    @BeforeEach
    void setUp() {
        // Instantiate mock repository
        MockitoAnnotations.initMocks(this);

        // Instantiate service to test with mocked repository
        animalService = new AnimalService();
    }

    @Test
    void read() {

        // Define what is returned when mock repository is called
        Mockito.when(mockedAnimalRepository.read(ANIMAL_ID)).thenReturn(getSingleAnimal(ANIMAL_ID));

        // Simple assertion on name - test service with mock injected
        assertEquals("Gummiged", animalService.read(ANIMAL_ID).getName());

        // Test that read gets called exactly once when service is called
        Mockito.verify(mockedAnimalRepository, times(1)).read(5);
    }

    private Animal getSingleAnimal(long id){
        return new Animal(id, "Gummiged", "entreprenørdyr");

    }

}