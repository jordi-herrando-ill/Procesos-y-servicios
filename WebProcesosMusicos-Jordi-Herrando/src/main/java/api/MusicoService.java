package api;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class MusicoService implements ImusicoService
{
    @Override
    public Set<Musico> findAll()
    {
        Set<Musico> musicos = new HashSet<>();

        musicos.add(new Musico("Sherpa", 73, "Los Barones", 55));
        musicos.add(new Musico("Fortu Sanchez", 69, "Obús",44 ));
        musicos.add(new Musico("Alberto Rionda", 51, "Avalanch", 27));
        musicos.add(new Musico("Niko Del Hierro", 57, "Saratoga",32 ));
        musicos.add(new Musico("Víctor García", 53, "WarCry", 37));
        musicos.add(new Musico("Txus Di Fellatio", 54, "Mägo de Oz", 36));
        musicos.add(new Musico("Carlos de Castro", 70, "Barón Rojo", 54));
        musicos.add(new Musico("Narci Lara", 45, "Saurom", 28));
        musicos.add(new Musico("Ángel San Juan", 56, "Tierra Santa", 27));
        musicos.add(new Musico("José Andrëa", 52, "Uroboros", 30));

        return musicos;
    }

}