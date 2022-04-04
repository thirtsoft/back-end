package com.spring.restaurant.controller;


import com.spring.restaurant.controller.api.UtilisateurApi;
import com.spring.restaurant.domaine.Response;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.model.Utilisateur;
import com.spring.restaurant.service.UtilisateurService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UtilisateurController implements UtilisateurApi {

    @Autowired
    ServletContext context;

    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public ResponseEntity<List<Utilisateur>> getListUtulisateurs() {
        List<Utilisateur> utilisateurList = utilisateurService.findAllUtilisateurs();
        return new ResponseEntity<>(utilisateurList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurOrderDesc() {
        List<Utilisateur> utilisateurList = utilisateurService.findAllUtilisateursOrderDesc();
        return new ResponseEntity<>(utilisateurList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable(value = "id") Long idUser)
            throws ResourceNotFoundException {
        Utilisateur utilisateur = utilisateurService.findUtilisateurById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur that id is" + idUser + "not found"));
        return ResponseEntity.ok().body(utilisateur);
    }

    @Override
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
        Utilisateur user = utilisateurService.findUtilisateurById(id).get();
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/") + user.getPhoto()));
    }

    @Override
    public void editPhotoUser(@RequestParam("image") MultipartFile file, @RequestParam("id") String id)
            throws Exception {
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResponseEntity<Response> editUser(@RequestParam("image") MultipartFile file, @RequestParam("user") String user)
            throws Exception {

        Utilisateur userIm = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(user, Utilisateur.class);

        boolean isExit = new File(context.getRealPath("/Images/")).exists();

        if (!isExit) {
            new File(context.getRealPath("/Images/")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }

        userIm.setPhoto(newFileName);
        Utilisateur user3 = utilisateurService.saveUtilisateur(userIm);
        if (user3 != null) {
            return new ResponseEntity<Response>(new Response(""), HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(new Response("Article not saved"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public byte[] getPhotoUser(@PathVariable("id") Long id) throws Exception {
        Utilisateur utilisateur = utilisateurService.findUtilisateurById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur that id is" + id + "not found"));
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/users/photos/" + utilisateur.getPhoto()));
    }

    @Override
    public void uploadUserPhoto(MultipartFile file, @PathVariable("id") Long id) throws IOException {
        Utilisateur utilisateur = utilisateurService.findUtilisateurById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur that id is" + id + "not found"));
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            utilisateur.setPhoto(filename);

            utilisateurService.saveUtilisateur(utilisateur);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadUserPhotoToDB(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) throws IOException {
        byte[] bytes = file.getBytes();
        String encodedFile = new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
        Utilisateur utilisateur = utilisateurService.findUtilisateurById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur that id is" + id + "not found"));
        utilisateur.setPhoto("data:image/jpeg;base64;" + encodedFile);
        utilisateurService.saveUtilisateur(utilisateur);
    }

    @Override
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable(value = "idUser") Long idUser, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(idUser);
        Utilisateur savingUser = utilisateurService.saveUtilisateur(utilisateur);
        if (savingUser != null) {
            return new ResponseEntity<>(savingUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<?> activatedUser(@RequestParam("isActive") String isActive, @PathVariable("id") String id) {
        Utilisateur activatedUtilisateur = utilisateurService.activatedUser(isActive, id);
        return new ResponseEntity<>(activatedUtilisateur, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteUtilisateur(@PathVariable(value = "id") Long idUser) {
        utilisateurService.deleteUtilisateur(idUser);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Boolean> updateUsername(@RequestBody ObjectNode json) {
        String username;
        String newUsername;
        try {
            username = new ObjectMapper().treeToValue(json.get("username"), String.class);
            newUsername = new ObjectMapper().treeToValue(json.get("newUsername"), String.class);
            boolean existsUser = this.utilisateurService.updateUsernameOfUtilisateur(username, newUsername);
            if (existsUser)
                return new ResponseEntity<>(existsUser, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            System.out.println("Parsing Exception");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Boolean> updatePassword(@RequestBody ObjectNode jsonNodes) {
        String username;
        String oldPassword;
        String newPassword;

        try {
            username = new ObjectMapper().treeToValue(jsonNodes.get("username"), String.class);
            oldPassword = new ObjectMapper().treeToValue(jsonNodes.get("oldPassword"), String.class);
            newPassword = new ObjectMapper().treeToValue(jsonNodes.get("newPassword"), String.class);

            boolean existUser = this.utilisateurService.updatePasswordofUtilisateur(username, oldPassword, newPassword);
            if (existUser)
                return new ResponseEntity<>(existUser, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            System.out.println("Parsing Exception");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }


}
