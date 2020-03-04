package dive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DiveController {

    @Autowired
    DiveRepository diveRepository;

    @Autowired
    DiverRepository diverRepository;

    @GetMapping("/dives/all")
    public List<Dive> getAllDives(){
        return diveRepository.findAll();
    }

    @GetMapping("/divers/all")
    public List<Diver> getAllDivers(){
        return diverRepository.findAll();
    }

    @GetMapping("/dives")
    public Dive getDive(@RequestParam(value="diveId") String id) {
        int diveId = Integer.parseInt(id);
        return diveRepository.findById(diveId).get();
    }

    @GetMapping("/divers")
    public List<Diver> getDivers(@RequestParam(value="diveId")  String id) {
        int diveId = Integer.parseInt(id);
        return diverRepository.findByDiveId_DiveId(diveId);
    }

    @PostMapping("/dives")
    public Dive createDive(@RequestBody Map<String, String> body) {

        String latitude = body.get("latitude");
        String longitude = body.get("longitude");
        String diveSite = body.get("diveSite");
        String location = body.get("location");

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
        String hyperbaricLocation= body.get("nearestHyperbaricChamber");
        String hemsLocation= body.get("nearestHemsUnit");
        String emsPhone= body.get("emsPhoneNumber");
        String coastguardPhone= body.get("coastguardPhoneNumber");
        String DANnumber= body.get("DANPhoneNumber");

        return diveRepository.save(new Dive(location,diveSite,longitude,latitude,DANnumber,maxDepth, totalBottomTime, visibility, environment, seaConditions, current, entryTime, exitTime, diveDifficulty, parking, hyperbaricLocation, hemsLocation, emsPhone, coastguardPhone));
    }

    @PostMapping("/divers")
    public Diver createDiver(@RequestBody Map<String, String> body) {
        String diveId = body.get("diveId");
        String name   = body.get("name");
        String phone  = body.get("phoneNumber");
        String gasBlend        = body.get("gasBlend");
        String exposureSuit    = body.get("exposureSuit");
        String breathingApparatus  = body.get("breathingApparatus");
        String qualifications    = body.get("qualifications");
        String medicalHistory    = body.get("medicalHistory");

        Diver diver = new Diver(name, phone, gasBlend, exposureSuit, breathingApparatus, qualifications, medicalHistory);

        int diveIdInt = Integer.parseInt(diveId);
        Dive dive = diveRepository.findById(diveIdInt).get();
        diver.setDiveId(dive);

        return diverRepository.save(diver);
    }

    @RequestMapping(value = "/android/download", method = RequestMethod.GET, produces="application/apk")
    public ResponseEntity<InputStreamResource> download() throws IOException {

        File file = new File("/usr/appstore/scubaSOS.apk");
        if (!file.exists()) {
            file = new File("D:/scubaSOS.apk");
            if(!file.exists()) {
                throw new FileNotFoundException("Oops! File not found");
            }
        }

        InputStreamResource isResource = new InputStreamResource(new FileInputStream(file));
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        String fileName = FilenameUtils.getName(file.getAbsolutePath());
        fileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.setContentLength(fileSystemResource.contentLength());
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<InputStreamResource>(isResource, headers, HttpStatus.OK);
    }
}
