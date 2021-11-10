import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public ResponseEntity<List<PaisDTO>> getAll(){
        //save pais
        List<PaisDTO> paises = paisService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(paises);
    }
    @PostMapping
    public ResponseEntity <PaisDTO> save(@RequestBody PaisDTO pais){
        //sabe pais
        PaisDTO paisGuardado = paisService.save(pais);
        return ResponseEntity.status(HttpStatus.CREATED).body(paisGuardado);
    }
}