package com.zrimmyjr.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pet {

    @Id
    @SequenceGenerator(
            name = "pet_id_sequence",
            sequenceName = "pet_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pet_id_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastName;
}
