package dive;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DiveController {

    @Autowired
    DiveRepository diveRepository;

    @GetMapping("/")
    public List<Dive> index(){
        return diveRepository.findAll();
    }

    @GetMapping("/dives")
    public Optional<Dive> get(@RequestParam(value="diveId", defaultValue="0") String id) {
        int diveId = Integer.parseInt(id);
        return diveRepository.findById(diveId);
    }

    @PostMapping("/dives")
    public Dive create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String maxDepth = body.get("maxDepth");
        String totalBottomTime = body.get("totalBottomTime");
        String phone = body.get("phone");
        return diveRepository.save(new Dive(name,maxDepth,totalBottomTime,phone));
    }

}