package application;


import application.request.CreateCustomerRequest;
import core.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateCustomerRequest request){

        customerService.createCustomer(request.toPerson());

        return ResponseEntity.ok("Customer created successfully");
    }


}
