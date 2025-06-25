package kg.megacom.portal.controllers;

import kg.megacom.portal.models.dto.RepairRequestDTO;
import kg.megacom.portal.models.entities.RepairRequest;
import kg.megacom.portal.services.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/repairRequests")
public class RepairRequestController {

    @Autowired
    private RepairRequestService repairRequestService;

    @PostMapping("/create")
    public ResponseEntity<Void> createRepairRequest(@RequestParam String location,
                                                    @RequestParam String description) {
        repairRequestService.createRepairRequest(location, description);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RepairRequestDTO>> getRepairRequests() {
        List<RepairRequestDTO> repairRequestList = repairRequestService.getAllRepairRequests();
        return ResponseEntity.ok().body(repairRequestList);
    }

}
