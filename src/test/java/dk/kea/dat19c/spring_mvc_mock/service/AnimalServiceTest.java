package dk.kea.dat19c.spring_mvc_mock.service;

import dk.kea.dat19c.spring_mvc_mock.model.Animal;
import dk.kea.dat19c.spring_mvc_mock.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class AnimalServiceTest {

    private static final long ANIMAL_ID = 5;
    @InjectMocks
    AnimalService animalService; // initialise Service to test with Mocks

    @Mock
    AnimalRepository mockedAnimalRepository; // Mocked repository that will return testdata


    @BeforeEach
    void setUp() {
        // Instantiate mock repository
        MockitoAnnotations.initMocks(this);


    }

    @Test
    void read() {

        Animal animal = new Animal(ANIMAL_ID, "Gummiged", "Entrepreneur");

        // Define what is returned when mock repository is called
        Mockito.when(mockedAnimalRepository.read(ANIMAL_ID)).thenReturn(animal);

        // Simple assertion on name - test service with mock injected
        assertEquals("Gummiged", animalService.read(ANIMAL_ID).getName());

        // Test that read gets called exactly once when service is called
        Mockito.verify(mockedAnimalRepository, times(1)).read(5);
    }

}