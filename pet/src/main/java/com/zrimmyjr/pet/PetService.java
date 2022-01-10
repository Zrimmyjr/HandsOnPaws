package com.zrimmyjr.pet;

import org.springframework.stereotype.Service;

@Service
public record PetService(PetRepository petRepository) {
    public void registerPet(PetRegistrationRequest request) {
        Pet pet = Pet.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();
        petRepository.save(pet);
    }
}
