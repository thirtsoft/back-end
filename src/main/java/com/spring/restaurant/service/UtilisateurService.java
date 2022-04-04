package com.spring.restaurant.service;

import com.spring.restaurant.enums.RoleName;
import com.spring.restaurant.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    Utilisateur saveUtilisateur(Utilisateur utilisateur);

    Utilisateur updateUtilisateur(Long idUser, Utilisateur utilisateur);

    Optional<Utilisateur> findUtilisateurById(Long idUser);

    void addRoleToUser(String username, RoleName roleName);

    boolean updateUsernameOfUtilisateur(String username, String newUsername);

    boolean updatePasswordofUtilisateur(String username, String oldPass, String newPass);

    Utilisateur activatedUser(String isActive, String id);

    List<Utilisateur> findAllUtilisateurs();

    List<Utilisateur> findAllUtilisateursOrderDesc();

    void deleteUtilisateur(Long idUser);
}
