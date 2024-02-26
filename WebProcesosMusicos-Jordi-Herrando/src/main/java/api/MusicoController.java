package api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
public class MusicoController
{
    @Autowired
    private ImusicoService MusicoService;

    @GetMapping(value = "/musico")
    public ResponseEntity<Set<Musico>> getmusico()
    {
        Set<Musico> musicos = MusicoService.findAll();
        return new ResponseEntity<>(musicos, HttpStatus.OK);
    }
}