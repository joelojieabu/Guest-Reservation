package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{
    //define field for entity manager
    private EntityManager entityManager;
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager)
    {

        this.entityManager = theEntityManager;
    }
    //setup constructor injection


    @Override
        public List<Employee> findAll() {
            //get the current hibernate session
            Session currentSession = entityManager.unwrap(Session.class);
            //create a query
            Query<Employee> theQuery = currentSession.createQuery("from Employee",Employee.class);
            //execute query and get result list
            List<Employee> employees = theQuery.getResultList();
            //return the result
            return employees;
        }

    @Override
    public Employee findById(int theId) {
        //get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //get employee
        Employee theEmployee= currentSession.get(Employee.class, theId);
        //return the employee
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theEmployee);

    }

    @Override
    public void deleteById(int theId) {
        //get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //delete user by id
         Query theQuery= currentSession.createQuery("delete from Employee where id=:employeeId");
         theQuery.setParameter("employeeId",theId);
         theQuery.executeUpdate();
    }
}
