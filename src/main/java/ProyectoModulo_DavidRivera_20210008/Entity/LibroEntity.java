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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sep_Libro") //creando secuuencia de aunmento cunado se ingresen datos
    @SequenceGenerator(sequenceName = "sep_Libro", name = "sep_Libro",allocationSize = 1) //especificando que la secuencia sea de 1 en adelante
    @Column(name = "IDLIBRO")
    private Long idLibro;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "ISBN", unique = true) //aclarando que este espacio tiene que ser unico
    private String isbn;
    @Column(name = "AÑOPUBLICACION")
    private Long añopublicacion;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "AUTORID")
    private Long autorid;

}
