package com.ums.data;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class DataLoader implements ApplicationRunner {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(ApplicationArguments args)  {
        entityManager.createNativeQuery(
                "INSERT IGNORE INTO usergroup (id, name) VALUES\n" +
                        "  (1 , 'HR'),\n" +
                        "  (2  , 'DEV'),\n" +
                        "  (3  , 'PM');"
        ).executeUpdate();

        entityManager.createNativeQuery(
                "INSERT IGNORE INTO\n" +
                        "  user (id, first_name, last_name, password,user_name,usergroup_id,date)\n" +
                        "VALUES\n" +
                        "  (1, 'David', 'Moulton','{bcrypt}$2a$10$usFd.2lfQzOVG/N45uDr7emsFOenWpAtwjqmROMqevyqou/eG26rS','david',1 ,'12/03/1998'),\n" +
                        "  (2, 'Damian', 'Kowalski','{bcrypt}$2a$10$usFd.2lfQzOVG/N45uDr7emsFOenWpAtwjqmROMqevyqou/eG26rS','damian',1 ,'12/07/2000'),\n" +
                        "(3, 'Agnes', 'Obel','{bcrypt}$2a$10$usFd.2lfQzOVG/N45uDr7emsFOenWpAtwjqmROMqevyqou/eG26rS','agi',2,'23/02/1995' ),\n" +
                "  (4, 'Patrik', 'Velas','{bcrypt}$2a$10$usFd.2lfQzOVG/N45uDr7emsFOenWpAtwjqmROMqevyqou/eG26rS','patrik',3,'16/10/1980');"
        ).executeUpdate();
    }
}
