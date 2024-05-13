package br.com.api.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;

    private String licensePlate;

    private String model;

    private String color;

    public void validate() throws IllegalArgumentException {
        StringBuilder invalidFields = new StringBuilder();

        if (year < 1900 || year > 2100) {
            invalidFields.append("year, ");
        }
        if (licensePlate == null || licensePlate.isEmpty()) {
            invalidFields.append("licensePlate, ");
        }
        if (model == null || model.isEmpty()) {
            invalidFields.append("model, ");
        }
        if (color == null || color.isEmpty()) {
            invalidFields.append("color, ");
        }

        if (invalidFields.length() > 0) {
            // Remove a vírgula extra no final
            invalidFields.deleteCharAt(invalidFields.length() - 2);
            throw new IllegalArgumentException("Invalid fields: " + invalidFields);
        }
    }

}
