package db;

import entity.Course;
import entity.Registration;
import entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class FactoryConfiguration {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        File propFile = new File("resources/application.properties");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .loadProperties(propFile).build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Registration.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
                .build();

        return  metadata.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

//        Configuration configure = new Configuration().configure().addAnnotatedClass(Student.class)
//                .addAnnotatedClass(Course.class)
//                .addAnnotatedClass(Registration.class);
//
//        sessionFactory = configure.buildSessionFactory();
//    }
//
//    public static FactoryConfiguration getInstance(){
//        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
//    }
//
//    public Session getSession(){
//        return sessionFactory.openSession();
//    }
}
