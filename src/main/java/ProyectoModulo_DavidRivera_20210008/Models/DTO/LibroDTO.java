package ProyectoModulo_DavidRivera_20210008.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
public class LibroDTO {
    private Long idLibro;
    @NotBlank(message = "NO puede quedar nulo") //validar y espicicar al usiario sobre los datos que no pueden ser nulos
    private String titulo;
    @NotBlank(message = "El codigo no puede ser nulo")
    private String isbn;
    @NotBlank(message = "No puede ser nulo el año de publicacion")
    private Long añopublicacion;
    @NotBlank(message = "No puede quedar nulo")
    private String genero;
    @NotBlank(message = "no puede estar nulo")
    private Long autorid;
}
