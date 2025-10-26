package com.wigell.mc.Controller;

import com.wigell.mc.DAO.AdminDAO;
import com.wigell.mc.Entity.Address;
import com.wigell.mc.Entity.Bike;

import com.wigell.mc.Entity.Customer;
import com.wigell.mc.Service.AdminService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/admin/api/v1")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        log.info("Retrieved all customers.");

        return adminService.getAllCustomers();

    }


    @GetMapping("/customers/{id}")
    public Customer getCustomers(@PathVariable("id") int id) {
        adminService.getCustomerById(id);
        log.info("Retrieved customer: {}", id);
        return adminService.getCustomerById(id);
    }



    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer created = adminService.createCustomer(customer);
        log.info("customer with name: {} has been created", created);
        return ResponseEntity.ok(created);
    }



    @PostMapping("/customers/{customerId}/addresses")
    public ResponseEntity<Address> addAddress(@PathVariable("customerId") Long customerId, @RequestBody Address address) {
        Address created = adminService.addAddress(customerId, address);
        log.info("address: {} has been created: ", created);
        return ResponseEntity.ok(created);
    }



    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        Customer updated = adminService.updateCustomer(id, customer);
        log.info("customer has been updated: {}", updated);
        return ResponseEntity.ok(updated);
    }




    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        boolean deleted = adminService.deleteCustomer(id);
        log.info("customer has been deleted: {}", id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }



    @DeleteMapping("/customers/{customerId}/addresses/{addressId}")
    public ResponseEntity<Address> deleteAddress(
            @PathVariable("customerId") Long customerId,
            @PathVariable("addressId") Long addressId) {
        boolean deleted = adminService.deleteAddressById(customerId, addressId);
        log.info("address has been deleted: {}", addressId);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();

    }



}
