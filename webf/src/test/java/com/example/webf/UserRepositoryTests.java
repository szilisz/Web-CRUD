package com.example.webf;


import com.example.webf.user.User;
import com.example.webf.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("dori@gmail.com");
        user.setPassword("13524");
        user.setFirstName("Dora");
        user.setLastName("Merk");

        User savedUser = repo.save(user);

        Assertions.assertTrue(savedUser.getId()>0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();

        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId=1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertTrue(updatedUser.getPassword().equals("hello"));

    }
}
