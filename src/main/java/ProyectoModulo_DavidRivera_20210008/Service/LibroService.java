package ProyectoModulo_DavidRivera_20210008.Service;

import ProyectoModulo_DavidRivera_20210008.Entity.LibroEntity;
import ProyectoModulo_DavidRivera_20210008.Models.DTO.LibroDTO;
import ProyectoModulo_DavidRivera_20210008.Repository.LibroRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LibroService {

    @Autowired
    private LibroRepository repo;

    public Page<LibroDTO> getAllLibros(int page, int size){
        Pageable pageable = QPageRequest.of(page, size);
        Page<LibroEntity> libro = repo.findAll(pageable);
        return libro.map(this::convertirALibrosDTO);
    }
    public LibroDTO createLibro(@Valid LibroDTO libroDTO) throws IllegalAccessException {
        if (libroDTO == null){
            throw new IllegalAccessException("El libro no asido encontradp");
        }
        try {
            LibroEntity libroEntity = convertirALibrosEntity(libroDTO);
            LibroEntity libroGuardado = repo.save(libroEntity);
            return convertirALibrosDTO(libroGuardado);
        }catch (Exception e){
            log.error("Libro no convertido" + e.getMessage());
        }
        return ();
    }
    public LibroDTO updateLibro(long id, @Valid LibroDTO libro){
        LibroEntity libroExistente = Optional.of(repo.findBy(id)).orElseThrow(()=> new Excep
    }

    private LibroEntity convertirALibrosEntity(@Valid LibroDTO libroDTO) {
        LibroEntity entity = new LibroEntity();
        entity.setIdLibro();
    }

    private LibroDTO convertirALibrosDTO(LibroEntity libroEntity) {
        LibroDTO dto = new LibroDTO();
        dto.setIdLibro(dto.getIdLibro());
    }
}
