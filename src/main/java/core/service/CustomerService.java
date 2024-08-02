package core.service;

import core.model.Person;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public Person createCustomer(Person person){

       if(person.isValid()){
           save(person);
       }

        return person;
    }

    private void save(Person person){
        throw new RuntimeException("Not implement yet");
    }
}
