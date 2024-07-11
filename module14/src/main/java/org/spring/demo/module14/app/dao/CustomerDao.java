package org.spring.demo.module14.app.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.spring.demo.module14.app.dao.model.Customer;
import org.spring.demo.module14.app.dao.model.Order;
import org.spring.demo.module14.app.exception.BadRequestException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerDao {

    private final SessionFactory sessionFactory;

    public Optional<Customer> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Customer.class, id));
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

    public Optional<Customer> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);

            Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), email);
            query.select(root).where(emailPredicate);

            Customer customer = session.createQuery(query).getSingleResult();
            transaction.commit();
            return Optional.ofNullable(customer);
        }
    }

//    public Optional<Customer> findByEmail(String email) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<CustomerProjection> query = criteriaBuilder.createQuery(CustomerProjection.class);
//            Root<Customer> root = query.from(Customer.class);
//
//            Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), email);
//            query.select(criteriaBuilder.construct(CustomerProjection.class,
//                    root.get("firstName"),
//                    root.get("lastName"))).where(emailPredicate);
//
//            CustomerProjection customer = session.createQuery(query).getSingleResult();
//            transaction.commit();
//
//            return Optional.ofNullable(Customer.builder()
//                    .firstName(customer.getFirstName())
//                    .lastName(customer.getLastName())
//                    .build());
//        }
//    }

    public Optional<List<Customer>> findAll(Integer max, Integer offset) {
        try (Session session = sessionFactory.openSession()) {
//            Query<Customer> fromCustomer = session.createQuery("FROM Customer", Customer.class);
            Query<Customer> fromCustomer = session.createQuery(
                    "SELECT c FROM Customer c LEFT JOIN FETCH c.orders " +
                    "LEFT JOIN c.customerDetails",
                    Customer.class);

            fromCustomer.setFirstResult(offset != null ? offset : 0);
            fromCustomer.setMaxResults(max != null ? max : 0);

            var result =  fromCustomer.list();
            result.forEach(System.out::println);
            return Optional.of(result);
        }
    }


    public Customer create(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(customer);
            tx.commit();
        }
        return customer;
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.find(Customer.class, id);
            session.remove(customer);
            transaction.commit();
        }
    }
}
