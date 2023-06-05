package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"/{customerID}"})
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerID") UUID customerID) {
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerByID(customerID), HttpStatus.OK);
    }

    @PostMapping // Create new Beer
    public ResponseEntity handlePost(@Valid @RequestBody CustomerDTO customerDTO){
        CustomerDTO savedDTO = customerService.saveNewCustomer(customerDTO);
        HttpHeaders headers = new HttpHeaders();
        // todo add hostname to url
        headers.add("Location","/api/v1/customer" + savedDTO.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping({"/{customerID}"})
    public ResponseEntity handlePut(@PathVariable("customerID") UUID customerID,@Valid @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerID,customerDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{customerID}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerID") UUID customerID){
        customerService.deleteCustomer(customerID);

    }

}
