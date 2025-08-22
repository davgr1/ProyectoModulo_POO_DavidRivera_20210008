package ProyectoModulo_DavidRivera_20210008.Repository;

import ProyectoModulo_DavidRivera_20210008.Entity.LibroEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
    @Override
    Page<LibroEntity> findAll(Pageable pageable);
}
