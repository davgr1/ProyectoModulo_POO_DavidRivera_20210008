package ProyectoModulo_DavidRivera_20210008.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "LIBROS")
@Getter @Setter @ToString @EqualsAndHashCode
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "")
    @SequenceGenerator(sequenceName = "", name = "",allocationSize = 1)
    @Column(name = "IDLIBRO")
    private Long idLibro;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "ISBN", unique = true)
    private String isbn;
    @Column(name = "AÑOPUBLICACION")
    private Long añopublicacion;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "AUTORID")
    private Long autorid;

}
