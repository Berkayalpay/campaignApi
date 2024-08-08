package ba.campaign.poc.api.campaign.webApi.Controllers;

import ba.campaign.poc.api.campaign.business.abstracts.CampaignService;
import ba.campaign.poc.api.campaign.dtos.request.CreateCampaignRequest;
import ba.campaign.poc.api.campaign.dtos.request.DeactivateCampaignRequest;
import ba.campaign.poc.api.campaign.dtos.request.UpdateCampaignStatusRequest;
import ba.campaign.poc.api.campaign.dtos.response.CampaignCountResponse;
import ba.campaign.poc.api.campaign.dtos.response.CampaignDetailsResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping("/create")
    public ResponseEntity<CampaignDetailsResponse> addCampaign(@Valid @RequestBody CreateCampaignRequest createCampaignRequest) {
        CampaignDetailsResponse savedCampaign = campaignService.addCampaign(createCampaignRequest);
        return ResponseEntity.ok(savedCampaign);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Void> updateCampaignStatus(@RequestBody UpdateCampaignStatusRequest updateCampaignStatusRequest) {
        campaignService.updateCampaignStatus(updateCampaignStatusRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/deactivateStatus")
    public ResponseEntity<Void> deactivateCampaignStatus(@RequestBody DeactivateCampaignRequest deactivateCampaignRequest) {
        campaignService.deactivateCampaignStatus(deactivateCampaignRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistics/count-by-status")
    public CampaignCountResponse countCampaignsByStatus() {
        return campaignService.countCampaign();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable int id) {
        campaignService.deleteCampaignById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
