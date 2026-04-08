package fms.Distopia.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom de la ville est obligatoire")
    private String name;

    @NotNull(message = "Le code postal de la ville est obligatoire")
    private String zipcode;

    @OneToMany(mappedBy = "city")
    private Collection<Cinema> cinemas;

}
