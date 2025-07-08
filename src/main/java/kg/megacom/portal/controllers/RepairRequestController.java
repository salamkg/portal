package kg.megacom.portal.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.megacom.portal.models.dto.RepairRequestDTO;
import kg.megacom.portal.services.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/repairRequests")
@Tag(name = "Заявки", description = "Заявки на офисное обслуживание")
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
