package com.kaishengit.tms;

import com.kaishengit.tms.entity.Customer;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.exception.ServiceException;

import java.math.BigDecimal;

public interface CustomerService {
    void save(Customer customer);

    void saveCustomer(Customer customer, String ticketNum, BigDecimal price, TicketStore ticketStore) throws ServiceException;
}
