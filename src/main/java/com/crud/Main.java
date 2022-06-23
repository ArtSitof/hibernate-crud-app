package com.crud;

import com.crud.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Add employee to database
        // ==========================================================================
//        addEmployee(new Employee("Boris", "Vasiliev", "Sales", 550));
//        addEmployee(new Employee("Ivan", "Ivanov", "HR", 600));
//        addEmployee(new Employee("Aleks", "Sechenov", "Sales", 750));
//        addEmployee(new Employee("Bob", "Marley", "IT", 800));
//        addEmployee(new Employee("Peter", "Petrov", "IT", 660));

        // Get employee from database by ID
        // ==========================================================================
//        System.out.println(getEmployee(1));

        // Add and get employee
        // ==========================================================================
//        System.out.println(addAndGetEmployee(new Employee("Bob", "Dylan", "IT", 600)));

        // Get lists of all employees
        // ==========================================================================
//        System.out.println(getListEmployees());;

        // Get a list of all employees named Bob with
        // salaries above 700
        // ==========================================================================
//        System.out.println(getListEmployeesByNameAndSalary());

        // Edit selected object
        // ==========================================================================
//        updateSalaryEmployee(1);
//        deleteEmployeeById(2);

        // Delete employee by HQL Query
        // ==========================================================================
//        deleteEmployeeByHqlQuery();


    }

    // Add employee to database
    public static void addEmployee (Employee emp) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(emp);
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

    // Get employee from database by ID
    public static Employee getEmployee (int id) {
        SessionFactory factory = new Configuration()
                .configure("src/main/hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class, id);
            session.getTransaction().commit();
            return emp;
        }
        finally {
            factory.close();
        }
    }

    // Add and get employee
    public static Employee addAndGetEmployee(Employee emp) {
        SessionFactory factory = new Configuration()
                .configure("src/main/hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(emp);

            Employee employee = session.get(Employee.class, emp.getId());
            session.getTransaction().commit();
            return employee;
        }
        finally {
            factory.close();
        }
    }

    // Get lists of all employees
    public static List<Employee> getListEmployees () {
        SessionFactory factory = new Configuration()
                .configure("src/main/hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            List<Employee> emps = session.createQuery("from Employee")
                    .getResultList();
                session.getTransaction().commit();
                return emps;
        } finally {
            factory.close();
        }
    }

    // Get a list of all employees named Bob with
    // salaries above 700
    public static List<Employee> getListEmployeesByNameAndSalary () {
        SessionFactory factory = new Configuration()
                .configure("src/main/hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            List<Employee> emps = session.createQuery("from Employee " +
                            "where name = 'Bob' and salary > 700")
                    .getResultList();
            session.getTransaction().commit();
            return emps;
        } finally {
            factory.close();
        }
    }

    // Edit selected object
    public static void updateSalaryEmployee (int id) {
        SessionFactory factory = new Configuration()
                .configure("src/main/hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class, id);
            emp.setSalary(1500);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    // Delete employee by ID
    public static void deleteEmployeeById (int id) {
        SessionFactory factory = new Configuration()
                .configure("src/main/hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class, id);
            session.delete(emp);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    // Delete employee by HQL Query
    public static void deleteEmployeeByHqlQuery () {
        SessionFactory factory = new Configuration()
                .configure("src/main/hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete Employee where name = 'Aleks'")
                    .executeUpdate();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }


}
