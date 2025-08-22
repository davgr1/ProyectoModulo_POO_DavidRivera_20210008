package ProyectoModulo_DavidRivera_20210008.Service;

import ProyectoModulo_DavidRivera_20210008.Entity.LibroEntity;
import ProyectoModulo_DavidRivera_20210008.Models.DTO.LibroDTO;
import ProyectoModulo_DavidRivera_20210008.Models.Exceptions.ExceptionLibro;
import ProyectoModulo_DavidRivera_20210008.Repository.LibroRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class LibroService {

    @Autowired //insercion de
    private LibroRepository repo;

    public Page<LibroDTO> getAllLibros(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
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
            throw new ExceptionLibro("No encontrado " + e.getMessage());
        }
    }

    public LibroDTO updateLibro(Long id, @Valid LibroDTO libroDTO){
        LibroEntity libroExistente = repo.findById(id).orElseThrow();  new ExceptionLibro("Libro no encontrado");
        libroExistente.setTitulo(libroDTO.getTitulo());
        LibroEntity libroActualizado = repo.save(libroExistente);
        return convertirALibrosDTO(libroActualizado);
    }
    public boolean deleteLibro(Long id){
        try {
            LibroEntity objLibro = repo.findById(id).orElse(null);
            if (objLibro != null){
                repo.deleteById(id);
                return true;
            }else {
                System.out.println("Libro no eliminado");
                return false;
            }
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("No se encontro "+ id + "para eliminar", 1);
        }
    }

    private LibroEntity convertirALibrosEntity(@Valid LibroDTO dto) {
        LibroEntity enty = new LibroEntity();
        enty.setIdLibro(dto.getIdLibro());
        enty.setTitulo(dto.getTitulo());
        enty.setIsbn(dto.getIsbn());
        enty.setAñopublicacion(dto.getAñopublicacion());
        enty.setGenero(dto.getGenero());
        enty.setAutorid(dto.getAutorid());
        return enty;
    }

    private LibroDTO convertirALibrosDTO(LibroEntity enty) {
        LibroDTO dto = new LibroDTO();
        dto.setIdLibro(enty.getIdLibro());
        dto.setTitulo(enty.getTitulo());
        dto.setIsbn(enty.getIsbn());
        dto.setAñopublicacion(enty.getAñopublicacion());
        dto.setGenero(enty.getGenero());
        dto.setAutorid(enty.getAutorid());
        return dto;
    }
}
