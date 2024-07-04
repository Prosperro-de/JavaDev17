package org.example.hibernate.app.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.hibernate.app.dao.config.HibernateUtil;
import org.example.hibernate.app.dao.model.Customer;
import org.example.hibernate.app.dao.model.Order;
import org.example.hibernate.app.dao.model.projection.CustomerProjection;
import org.example.hibernate.app.exception.BadRequestException;
import org.example.hibernate.app.util.RequestToEntityMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CustomerDao {
    private static CustomerDao INSTANCE;
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public static CustomerDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerDao();
        }
        return INSTANCE;
    }

    public Customer findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            var customer = session.find(Customer.class, id);
//            customer.getOrders().forEach(System.out::println);
//            customer.getOrders().forEach(System.out::println);
            return customer;
        }
    }

    public void saveOrderForCustomer(Long id, Order order) {
        try (Session session = sessionFactory.openSession()) {

            var customer = session.find(Customer.class, id);

            customer.addOrder(order);
            session.persist(order);
        }
    }

//    public Customer findByEmail(String email) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            Query<Customer> query = session.createQuery(
//                    "FROM Customer WHERE email = :email", Customer.class);
//            query.setParameter("email", email);
//
//            Customer customer = query.list().stream()
//                    .findFirst()
//                    .orElseThrow(BadRequestException::new);
//
//            transaction.commit();
//            return customer;
//        }
//    }

//    public Customer findByEmail(String email) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
//            Root<Customer> root = query.from(Customer.class);
//
//            Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), email);
//            query.select(root).where(emailPredicate);
//
//            Customer customer = session.createQuery(query).getSingleResult();
//            transaction.commit();
//            return customer;
//        }
//    }

    public Customer findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CustomerProjection> query = criteriaBuilder.createQuery(CustomerProjection.class);
            Root<Customer> root = query.from(Customer.class);

            Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), email);
            query.select(criteriaBuilder.construct(CustomerProjection.class,
                    root.get("firstName"),
                    root.get("lastName"))).where(emailPredicate);

            CustomerProjection customer = session.createQuery(query).getSingleResult();
            transaction.commit();

            return Customer.builder()
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .build();
        }
    }

    public List<Customer> findAll(Integer max, Integer offset) {
        try (Session session = sessionFactory.openSession()) {
//            Query<Customer> fromCustomer = session.createQuery("FROM Customer", Customer.class);
            Query<Customer> fromCustomer = session.createQuery(
                    "SELECT c FROM Customer c LEFT JOIN FETCH c.orders " +
                    "LEFT JOIN c.customerDetails",
                    Customer.class);

            fromCustomer.setFirstResult(offset);
            fromCustomer.setMaxResults(max);

            var result =  fromCustomer.list();
            result.forEach(System.out::println);
            return result;
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

    public Customer create(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(customer);
            tx.commit();
        }
        return customer;
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
