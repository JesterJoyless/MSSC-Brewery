package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{
    @Override
    public CustomerDTO getCustomerByID(UUID customerID) {
        return CustomerDTO.builder().id(UUID.randomUUID())
                .CustomerName("Customer1")
                .build();
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder().id(UUID.randomUUID())
                .CustomerName("Customer1")
                .build();
    }

    @Override
    public void updateCustomer(UUID customerID, CustomerDTO customerDTO) {
        // todo impl - add updated Existing customer
    }

    @Override
    public void deleteCustomer(UUID customerID) {
        log.debug("Deleting Customer: (" + customerID +").....");
    }
}
