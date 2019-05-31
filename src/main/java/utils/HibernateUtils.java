package utils;

import entity.Department;
import entity.Lector;
import lombok.extern.log4j.Log4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Log4j
public class HibernateUtils {
    //mapping the Hibernate SessionFactory object as singleton
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration().configure();
            config.addAnnotatedClass(Lector.class);
            config.addAnnotatedClass(Department.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
            sessionFactory = config.buildSessionFactory(builder.build());
        } catch (Throwable e) {
            log.error("Error in creating SessionFactory object."
                    + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

