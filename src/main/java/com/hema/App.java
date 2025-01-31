package com.hema;

import model.Department;
import model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        oneToMany();
        manyToOne();
    }

    public static void manyToOne() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        //creating departments
        Department dept1 = new Department("IT");
        Department dept2 = new Department("HR");

        //creating teacher
        Teacher t1 = new Teacher(1,"1000", "MHaseeb");
        Teacher t2 = new Teacher(2,"2220","Shahparan");
        Teacher t3 = new Teacher(3,"3000","James");
        Teacher t4 = new Teacher(4,"40000", "Joseph");

        //Storing Departments in database
        session.persist(dept1);
        session.persist(dept2);
        //Storing teachers in database
        session.persist(t1);
        session.persist(t2);
        session.persist(t3);
        session.persist(t4);
        transaction.commit();
    }
    public static void oneToMany(){
        SessionFactory factory = new
                Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        //Creating Department Object
        Department department = new Department("Development");

        //creating teacher with department
        Teacher t1 = new Teacher("1000","MHaseeb", department);
        Teacher t2 = new Teacher("2220","Shahparan", department);
        Teacher t3 = new Teacher("3000","James", department);
        Teacher t4 = new Teacher("40000","Joseph", department);
        Teacher t5 = new Teacher("200","Ali", department);

        //Add teacher entity object to the list
        ArrayList<Teacher> teachersList = new ArrayList<>();
        teachersList.add(t1);
        teachersList.add(t2);
        teachersList.add(t3);
        teachersList.add(t4);
        teachersList.add(t5);


        department.setTeacherList(teachersList);

        //Storing Department
        session.persist(department);

        //persist teachers
        session.persist(t1);
        session.persist(t2);
        session.persist(t3);
        session.persist(t4);
        session.persist(t5);


        t.commit();
    }
}
