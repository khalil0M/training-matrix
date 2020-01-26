package com.humanup.matrix.training.trainingmatrix.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"id","name","address","phone","email"})
public class TrainerVO implements Serializable {
    long id;
    String name;
    String address;
    String phone;
    String email;
}
