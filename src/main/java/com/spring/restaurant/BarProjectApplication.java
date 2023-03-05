package com.spring.restaurant;

import com.spring.restaurant.deo.CategoryRepository;
import com.spring.restaurant.deo.OrderRepository;
import com.spring.restaurant.deo.RoleRepository;
import com.spring.restaurant.deo.UtilisateurRepository;
import com.spring.restaurant.enums.RoleName;
import com.spring.restaurant.model.Category;
import com.spring.restaurant.model.Role;
import com.spring.restaurant.model.Utilisateur;
import com.spring.restaurant.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAsync
public class BarProjectApplication implements CommandLineRunner {


    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final UtilisateurService utilisateurService;

    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public BarProjectApplication(UtilisateurRepository utilisateurRepository,
                                 RoleRepository roleRepository,
                                 UtilisateurService utilisateurService, CategoryRepository categoryRepository, OrderRepository orderRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.utilisateurService = utilisateurService;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BarProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

/*
        Role vendorRole = roleRepository.save(new Role(RoleName.ROLE_VENDEUR));
        Role adminRole = roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        Utilisateur admin = new Utilisateur();
        admin.setId(1L);
        admin.setUsername("Admin");
        admin.setName("Admin");
        admin.setEmail("admin@gmail.com");
        admin.setActive(true);
        admin.setPassword(bCryptPasswordEncoder.encode("Admin123456"));
        utilisateurRepository.save(admin);

        Utilisateur vendor = new Utilisateur();
        vendor.setId(2L);
        vendor.setUsername("Vendeur");
        vendor.setName("Vendeur");
        vendor.setEmail("vendeur@gmail.com");
        vendor.setActive(true);
        vendor.setPassword(bCryptPasswordEncoder.encode("Vendeur123456"));
        utilisateurRepository.save(vendor);

        utilisateurService.addRoleToUser("Admin", RoleName.ROLE_ADMIN);

        */


        /*
        Utilisateur vendor = new Utilisateur();
        vendor.setId(2L);
        vendor.setUsername("Vendeur");
        vendor.setName("Vendeur");
        vendor.setEmail("vendeur@gmail.com");
        vendor.setActive(true);
        vendor.setPassword(bCryptPasswordEncoder.encode("Vendeur123456"));
        utilisateurRepository.save(vendor);
        */

        /*
        utilisateurService.addRoleToUser("Admin", RoleName.ROLE_ADMIN);
        utilisateurService.addRoleToUser("Vendeur", RoleName.ROLE_VENDEUR);*/


    }
}