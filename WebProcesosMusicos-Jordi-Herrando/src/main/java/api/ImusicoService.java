package api;

import org.springframework.http.ResponseEntity;

import java.util.Set;


public interface ImusicoService {
    Set<Musico> findAll();
    /*Set<musico> findByCategory(String category);
    Optional<musico> findById(long id);
    musico addmusico(musico musico);
    musico modifymusico(long id, musico newmusico);
    void deletemusico(long id);*/
}
