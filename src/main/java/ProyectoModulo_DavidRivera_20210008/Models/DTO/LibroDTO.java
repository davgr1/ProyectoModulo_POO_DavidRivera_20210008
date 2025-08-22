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
    @NotBlank
    private String titulo;
    @NotBlank
    private String isbn;
    @NotBlank
    private Long añopublicacion;
    @NotBlank
    private String genero;
    @NotBlank
    private Long autorid;
}
