package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDTO;
import java.util.UUID;

public interface CustomerService {
    CustomerDTO getCustomerByID(UUID customerID);

    CustomerDTO saveNewCustomer(CustomerDTO customerDTO);

    void updateCustomer(UUID customerID, CustomerDTO customerDTO);

    void deleteCustomer(UUID customerID);
}
