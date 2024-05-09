package com.tw.bootcamp.bookshop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializationComponent implements CommandLineRunner {
    private final UserRepository userRepository;

    @Autowired
    public DataInitializationComponent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        initializeAdminUser();
    }

    private void initializeAdminUser() {

        String adminUserName = "adminbookshop@gmail.com";

        if(!userRepository.findByEmail(adminUserName).isPresent()){

            User adminUser = User.builder()
                    .email(adminUserName)
                    .password("admin_user")
                    .role(Role.ADMIN)
                    .mobileNumber("1234567890")
                    .firstName("admin")
                    .lastName("user")
                    .build();
            userRepository.save(adminUser);
        }
    }


}
