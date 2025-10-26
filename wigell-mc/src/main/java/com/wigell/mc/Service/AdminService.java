package com.wigell.mc.Service;


import com.wigell.mc.DAO.AdminDAO;
import com.wigell.mc.Entity.Address;
import com.wigell.mc.Entity.Bike;
import com.wigell.mc.Entity.Bookings;
import com.wigell.mc.Entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class AdminService {
    private final AdminDAO adminDAO;


    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Transactional
    public List<Customer> getAllCustomers() {
        return adminDAO.getAllCustomers();
    }





    @Transactional
    public Customer getCustomerById(int id) {
        return adminDAO.getCustomerById(id);
    }








    @Transactional
    public Customer createCustomer(Customer customer) {
        return adminDAO.createCustomer(customer);
    }



    @Transactional
    public Address addAddress(Long customerId, Address address) {
        Customer customer = adminDAO.getCustomerById(Math.toIntExact(customerId));
        address.setCustomer(customer);
        return adminDAO.addAddress(customerId, address);
    }





    @Transactional
    public Customer updateCustomer(Long id, Customer customer) {
        Customer updatedCustomer = adminDAO.updateCustomer(id, customer);
        return updatedCustomer;

    }





    @Transactional
    public boolean deleteCustomer(Long id) {
        return adminDAO.deleteCustomer(id);
    }





    @Transactional
    public boolean deleteAddressById(Long customerId, Long addressId) {return adminDAO.deleteAddressById(customerId, addressId);}





}
