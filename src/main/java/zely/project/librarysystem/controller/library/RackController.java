package zely.project.librarysystem.controller.library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zely.project.librarysystem.dto.library.RackDto;
import zely.project.librarysystem.service.library.RackService;

import java.util.List;

@RestController
@RequestMapping("/api/rack")
public class RackController {
    private final RackService rackService;
    public RackController(RackService rackService) {
        this.rackService = rackService;
    }

    @GetMapping
    public List<RackDto> getAllRacks(){

        List<RackDto> racks = rackService.getAllRacks();

        return ResponseEntity.ok(racks).getBody();
    }

    @GetMapping("/{id}")
    public RackDto getRackById(@PathVariable Integer id){

        return rackService.getRackById(id).orElseThrow(
                () -> new NotFoundException("Rack ID not found")
        );
    }

//    @GetMapping("/{libraryCode}")
//    public List<RackDto> getRacksByLibraryCode(@PathVariable LibraryCode libraryCode){
//
//        List<RackDto> racks = rackService.getRackByLibraryCode(libraryCode);
//
//        if (racks.isEmpty()) {
//            throw new NotFoundException("Racks not found for the provided library code");
//        }
//
//        return racks;
//    }

    @PostMapping
    public ResponseEntity<RackDto> createNewRack(@RequestBody RackDto rackDto){

        RackDto rackToBe = rackService.createNewRack(rackDto);

        return new ResponseEntity<>(rackToBe, HttpStatus.CREATED);


    }

    @PutMapping("/{id}")
    public ResponseEntity updateRackById(@PathVariable Integer id, @RequestBody RackDto rackDto){

        rackService.updateRackbyId(id, rackDto).orElseThrow(
                () -> new NotFoundException("id not found")
        );

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRackById(@PathVariable Integer id){

        if (!rackService.deleteRackById(id)){
            throw new NotFoundException("ID not found");
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
