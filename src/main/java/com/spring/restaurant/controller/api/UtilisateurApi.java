package com.spring.restaurant.controller.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.spring.restaurant.domaine.Response;
import com.spring.restaurant.exceptions.ResourceNotFoundException;
import com.spring.restaurant.model.Utilisateur;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.spring.restaurant.util.Constants.APP_ROOT;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

public interface UtilisateurApi {

    @GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Utilisateur>> getListUtulisateurs();

    @GetMapping(value = APP_ROOT + "/utilisateurs/allUtilisateurOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Utilisateur>> getAllUtilisateurOrderDesc();

    @GetMapping(value = APP_ROOT + "/utilisateurs/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable(value = "id") Long idUser)
            throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/utilisateurs/avatar/{id}")
    byte[] getPhoto(@PathVariable("id") Long id) throws Exception;

    @PutMapping(value = APP_ROOT + "/photo")
    void editPhotoUser(@RequestParam("image") MultipartFile file, @RequestParam("id") String id)
            throws Exception;

    @PutMapping(value = APP_ROOT + "/utilisateurs/user")
    ResponseEntity<Response> editUser(@RequestParam("image") MultipartFile file, @RequestParam("user") String user)
            throws Exception;

    @GetMapping(value = APP_ROOT + "/utilisateurs/photoUser/{id}", produces = IMAGE_PNG_VALUE)
    byte[] getPhotoUser(@PathVariable("id") Long id) throws Exception;

    @PostMapping(value = APP_ROOT + "/utilisateurs/uploadUserPhoto/{id}", produces = IMAGE_PNG_VALUE)
    void uploadUserPhoto(MultipartFile file, @PathVariable("id") Long id) throws IOException;


    @PostMapping(value = APP_ROOT + "/utlisateurs/uploadUserPhoto/{id}/uploadUserPhoto", produces = IMAGE_PNG_VALUE)
    void uploadUserPhotoToDB(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @PutMapping(value = APP_ROOT + "/utilisateurs/update/{idUser}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable(value = "idUser") Long idUser, @RequestBody Utilisateur utilisateur);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/activatedUser/{id}")
    ResponseEntity<?> activatedUser(@RequestParam("isActive") String isActive, @PathVariable("id") String id);

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{id}")
    ResponseEntity<Object> deleteUtilisateur(@PathVariable(value = "id") Long idUser);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updateUsername")
    ResponseEntity<Boolean> updateUsername(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updatePassword")
    ResponseEntity<Boolean> updatePassword(@RequestBody ObjectNode jsonNodes);


}
