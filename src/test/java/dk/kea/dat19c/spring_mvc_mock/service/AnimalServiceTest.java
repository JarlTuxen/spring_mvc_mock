package dk.kea.dat19c.spring_mvc_mock.service;

import dk.kea.dat19c.spring_mvc_mock.model.Animal;
import dk.kea.dat19c.spring_mvc_mock.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class AnimalServiceTest {

    private static final long ANIMAL_ID = 5;
    @InjectMocks
    AnimalService animalService; // initialise Service to test with Mocks

    @Mock
    AnimalRepository mockedAnimalRepository; // Mocked repository that will return testdata


    @BeforeEach
    void setUp() {
        // Instantiate mock repository
        // Mockito 3.3.6 skal være openMocks - man kan så explicit lave close i AfterEach:
        // https://www.javadoc.io/doc/org.mockito/mockito-core/3.4.0/org/mockito/MockitoAnnotations.html
        MockitoAnnotations.initMocks(this);

        //arrange
        //definer dummydata - ANIMAL_ID er defineret som konstant 5
        Animal animal = new Animal(ANIMAL_ID, "Gummiged", "Entrepreneur");
        // Define what is returned when mock repository is called
        Mockito.when(mockedAnimalRepository.read(ANIMAL_ID)).thenReturn(animal);
    }

    @Test
    void read() {
        //act
        String name = animalService.read(ANIMAL_ID).getName();

        //assert
        // Simple assertion on name - test service with mock injected
        assertEquals("Gummiged", name);

        // Test that read gets called exactly once when service is called
        //Mockito.verify(mockedAnimalRepository).read(4); //fejl da der forventes 5
        Mockito.verify(mockedAnimalRepository, times(1)).read(ANIMAL_ID);
    }

}