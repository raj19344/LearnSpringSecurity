package com.learningSpringSecurity.LearnSpringSecurity.dataLoader;
import com.learningSpringSecurity.LearnSpringSecurity.model.Authority;
import com.learningSpringSecurity.LearnSpringSecurity.model.Customer;
import com.learningSpringSecurity.LearnSpringSecurity.repository.AuthorityRepository;
import com.learningSpringSecurity.LearnSpringSecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AuthorityRepository authorityRepository;


    public void run(ApplicationArguments args) throws InterruptedException {

        authorityRepository.save(new Authority("USER"));
        authorityRepository.save(new Authority("VIEW"));
        authorityRepository.save(new Authority("ADMIN"));
 //       userRepository.save(new UserModel(3,"rahul","companyTwo","{noop}rahul","rahul@gmail.com",new RoleModel(6)));

        customerRepository.save(new Customer("neel@tcs.com","{noop}neel", new Authority(1L)));
        customerRepository.save(new Customer("raj@tcs.com","{noop}raj",new Authority(3L)));
        customerRepository.save(new Customer("preetish@tcs.com","{noop}preetish",new Authority(2L)));
    }

}