package com.zrimmyjr.pet;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/pets")
@AllArgsConstructor
public class PetController{

    private final PetService petService;

    @PostMapping
    public void registerPet(@RequestBody PetRegistrationRequest petRegistrationRequest) {
        log.info("New Pet Registration {}", petRegistrationRequest);
        petService.registerPet(petRegistrationRequest);
    }
}
