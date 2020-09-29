package dk.kea.dat19c.spring_mvc_mock.repository;

import dk.kea.dat19c.spring_mvc_mock.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepository implements ICrudRepository<Animal>{

    //collection for handling test data
    private List<Animal> animals = new ArrayList<>();

    public AnimalRepository(){
        //fill in test data
        this.animals.add(new Animal(1, "AbeNisseHval", "Husdyr"));
        this.animals.add(new Animal(2, "MotorStruds", "Havdyr"));
        this.animals.add(new Animal(3, "Kungfu Panda", "Kinesisk Panda"));
        this.animals.add(new Animal(4, "Snurre Snup", "Hare"));
    }

    @Override
    public List<Animal> readAll(){
        return animals;
    }

    @Override
    //add new animal to collection
    public void create(Animal animal){
        animals.add(animal);
    }

    @Override
    public Animal read(long id) {
        //find element med id
        int i=0;
        while (i<animals.size()){
            if (animals.get(i).getId()==id) return animals.get(i);
            i++;
        }
        return null;
    }

    @Override
    public boolean update(Animal animal) {
        //find element der skal opdateres i animals
        for (int i=0; i<animals.size();i++){
            if (animals.get(i).getId() == animal.getId()){
                //opdater element (erstat med nye udgave)
                animals.set(i, animal);
                return true;
            }
        }
        //element med id ikke fundet
        return false;
    }

    @Override
    public boolean delete(long id) {
        //find og slet element med id
        int i=0;
        while (i<animals.size()){
            if (animals.get(i).getId()==id){
                animals.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }
}
