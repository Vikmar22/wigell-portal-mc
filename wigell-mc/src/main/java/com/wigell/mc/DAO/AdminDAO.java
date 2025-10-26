package com.wigell.mc.DAO;

import com.wigell.mc.Entity.Address;
import com.wigell.mc.Entity.Bike;
import com.wigell.mc.Entity.Customer;
import com.wigell.mc.Entity.Bookings;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AdminDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Customer> getAllCustomers() {
        return em.createQuery("select c from Customer c", Customer.class)
                .getResultList();

    }





    public Customer getCustomerById(int id) {
        return em.createQuery("select c from Customer c where c.id = :id", Customer.class)
                .setParameter("id", id)
                .getSingleResult();
    }









    public Customer createCustomer(Customer customer) {
        em.merge(customer);
        return customer;
    }



    public Address addAddress(Long customerId, Address address) {
        em.merge(address);
        return address;
    }




    public Customer updateCustomer(Long id, Customer customerBody) {
        Customer existingCustomer = em.find(Customer.class, id);
        existingCustomer.setUserName(customerBody.getUserName());
        existingCustomer.setFirstName(customerBody.getFirstName());
        existingCustomer.setLastName(customerBody.getLastName());

        return existingCustomer;
    }





    public boolean deleteCustomer(Long id) {
        Customer existingCustomer = em.find(Customer.class, id);
        if (existingCustomer == null) return false;
        em.remove(existingCustomer);
        return true;
    }





    public boolean deleteAddressById(Long customerId, Long addressId) {
        Address existingAddress = em.find(Address.class, addressId);
        em.remove(existingAddress);
        return true;
    }






}
