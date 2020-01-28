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

    @Autowired
    DiverRepository diverRepository;

    @GetMapping("/dives")
    public List<Dive> getAllDives(){
        return diveRepository.findAll();
    }

    @GetMapping("/divers")
    public List<Diver> getAllDivers(){
        return diverRepository.findAll();
    }

    @GetMapping("/dives")
    public Optional<Dive> getDive(@RequestParam(value="diveId") String id) {
        int diveId = Integer.parseInt(id);
        return diveRepository.findById(diveId);
    }

    @GetMapping("/divers")
    public List<Diver> getDivers(@RequestParam(value="diveId")  String id) {
        int diveId = Integer.parseInt(id);
        return diverRepository.findByDiveid_DiveId(diveId);
    }

    @PostMapping("/dives")
    public Dive createDive(@RequestBody Map<String, String> body) {
        String maxDepth = body.get("maxDepth");
        String totalBottomTime = body.get("totalBottomTime");
        String visibility= body.get("visibility");
        String environment= body.get("environment");
        String seaConditions= body.get("seaConditions");
        String current= body.get("current");
        String entryTime= body.get("entryTime");
        String exitTime= body.get("exitTime");
        String diveDifficulty= body.get("diveDifficulty");
        String parking= body.get("parking");
        String hyperbaricLocation= body.get("hyperbaricLocation");
        String hemsLocation= body.get("hemsLocation");
        String emsPhone= body.get("emsPhone");
        String coastguardPhone= body.get("coastguardPhone");

        return diveRepository.save(new Dive(maxDepth, totalBottomTime, visibility, environment, seaConditions, current, entryTime, exitTime, diveDifficulty, parking, hyperbaricLocation, hemsLocation, emsPhone, coastguardPhone));
    }

    @PostMapping("/divers")
    public Diver createDiver(@RequestBody Map<String, String> body) {
        String name   = body.get("name");
        String phone  = body.get("phone");
        String gasBlend        = body.get("gasBlend");
        String exposureSuit    = body.get("exposureSuit");
        String breathingApparatus  = body.get("breathingAppartus");
        String qualifications    = body.get("qualifications");
        String medicalHistory    = body.get("medicalHistory");

        return diverRepository.save(new Diver(name, phone, gasBlend, exposureSuit, breathingApparatus, qualifications, medicalHistory));
    }










}