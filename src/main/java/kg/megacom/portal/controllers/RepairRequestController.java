package kg.megacom.portal.controllers;

import kg.megacom.portal.services.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
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

}
