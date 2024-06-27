package org.example.hibernate.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.hibernate.app.dao.config.HibernateUtil;
import org.example.hibernate.app.dao.model.Customer;
import org.example.hibernate.app.exception.BadRequestException;
import org.example.hibernate.app.util.RequestToEntityMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CustomerService {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    private ObjectMapper objectMapper = new ObjectMapper();

    public Customer findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Customer.class, id);
        }
    }

    public Customer findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Customer> query = session.createQuery(
                    "FROM Customer WHERE email = :email", Customer.class);
            query.setParameter("email", email);

            Customer customer = query.list().stream()
                    .findFirst()
                    .orElseThrow(BadRequestException::new);

            transaction.commit();
            return customer;
        }
    }


    public List<Customer> findAll(Integer max, Integer offset) {
        try (Session session = sessionFactory.openSession()) {
            Query<Customer> fromCustomer = session.createQuery("FROM Customer", Customer.class);
            fromCustomer.setFirstResult(offset);
            fromCustomer.setMaxResults(max);
            return fromCustomer.list();
        }
    }


    public Long create(Map<String, String[]> params) throws Exception {
        Customer customer = RequestToEntityMapper.mapToEntity(params, Customer.class);
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            if (customer.getEmail().isEmpty()) {
                throw new BadRequestException();
            }
            session.persist(customer);
            tx.commit();
        }
        return customer.getId();
    }

    public void delete(Long id) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.find(Customer.class, id);
            session.remove(customer);
            transaction.commit();
        }
    }
}
