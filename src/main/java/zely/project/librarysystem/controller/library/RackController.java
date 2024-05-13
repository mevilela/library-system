package zely.project.librarysystem.controller.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zely.project.librarysystem.dto.library.LibraryDto;
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
}
