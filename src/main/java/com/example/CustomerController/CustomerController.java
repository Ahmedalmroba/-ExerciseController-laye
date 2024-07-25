     package com.example.CustomerController;
     import com.example.Customer.Customer;
     import  org.springframework.web.bind.annotation.*;
     import java.util.ArrayList;
     import java.util.List;
     @RestController
     @RequestMapping("/api/v1/customers")
     public class CustomerController {
     private List<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
        public List<Customer> getAllCustomers() {
            return customers;
        }

        @PostMapping("/add")
        public String addCustomer(@RequestBody Customer customer) {
            customers.add(customer);
            return "Customer added";
        }

        @PutMapping("/update/{id}")
        public String updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
                    return "Customer updated";
                }

        @DeleteMapping("/{id}")
        public void deleteCustomer(@PathVariable int id) {
            customers.remove(id);
        }

        @PatchMapping("/{id}/deposit")
        public int depositMoney(@PathVariable int id, @RequestParam double amount) {
            for (Customer customer : customers) {
                if (customer.getId() == id) {
                    double currentBalance = customer.getBalance();
                    customer.setBalance(currentBalance + amount);
                    return customer.getId();
                }
            }
            throw new IllegalArgumentException("Customer not found with ID: " + id);
        }

        // Withdraw money from customer
        @PatchMapping("/{id}/withdraw")
        public int withdrawMoney(@PathVariable int id, @RequestParam double amount) {
            for (Customer customer : customers) {
                if (customer.getId() == id) {
                    double currentBalance = customer.getBalance();
                    if (currentBalance >= amount) {
                        customer.setBalance(currentBalance - amount);
                        return customer.getId();
                    }

                }
            }
            throw new IllegalArgumentException("Customer not found with ID: "+id);
}
    }


