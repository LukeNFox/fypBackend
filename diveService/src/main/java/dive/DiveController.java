package dive;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;


@RestController
public class DiveController {

    DiveMockedData data = DiveMockedData.getInstance();

    @GetMapping("/")
    public List<Dive> index(){
        return data.getDives();
    }

    @GetMapping("/dives")
    public Dive get(@RequestParam(value="diveId", defaultValue="0") String id) {
        int diveId = Integer.parseInt(id);
        return data.getDiveByID(diveId);
    }

    @PostMapping("/dives")
    public Dive create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String maxDepth = body.get("maxDepth");
        String totalBottomTime = body.get("totalBottomTime");
        String phone = body.get("phone");
        return data.createDive(name,maxDepth,totalBottomTime,phone);
    }

}