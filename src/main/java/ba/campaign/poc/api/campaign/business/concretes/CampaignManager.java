package ba.campaign.poc.api.campaign.business.concretes;

import ba.campaign.poc.api.campaign.business.abstracts.CampaignService;
import ba.campaign.poc.api.campaign.core.utilities.mappers.ModelMapperService;
import ba.campaign.poc.api.campaign.dataAccess.abstracts.CampaignRepository;
import ba.campaign.poc.api.campaign.dtos.request.CreateCampaignRequest;
import ba.campaign.poc.api.campaign.dtos.request.DeactivateCampaignRequest;
import ba.campaign.poc.api.campaign.dtos.request.UpdateCampaignStatusRequest;
import ba.campaign.poc.api.campaign.dtos.response.CampaignCountResponse;
import ba.campaign.poc.api.campaign.dtos.response.CampaignDetailsResponse;
import ba.campaign.poc.api.campaign.entities.Campaign;
import ba.campaign.poc.api.campaign.enumeration.CampaignStatus;
import ba.campaign.poc.api.campaign.rules.CampaignBusinessRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CampaignManager implements CampaignService {
    private final CampaignRepository campaignRepository;
    private final ModelMapperService modelMapperService;
    private final CampaignBusinessRules campaignBusinessRules;

    @Autowired
    public CampaignManager(CampaignRepository campaignRepository, ModelMapperService modelMapperService, CampaignBusinessRules campaignBusinessRules) {
        this.campaignRepository = campaignRepository;
        this.modelMapperService = modelMapperService;
        this.campaignBusinessRules = campaignBusinessRules;
    }

    @Override
    public CampaignDetailsResponse addCampaign(CreateCampaignRequest createCampaignRequest) {
        campaignBusinessRules.checkIfCampaignCategoryExists(createCampaignRequest.getCampaignCategory());
        Campaign campaign = modelMapperService.forRequest().map(createCampaignRequest, Campaign.class);
        boolean isDuplicate = checkDuplicate(createCampaignRequest);

        if (isDuplicate) {
            campaign.setCampaignStatus(CampaignStatus.MUKERRER);
            campaign.setDuplicate(true);
            List<Campaign> campaigns = campaignRepository.findAll();
            for (Campaign existingCampaign : campaigns) {
                if (existingCampaign.getCampaignCategory() == campaign.getCampaignCategory()
                        && existingCampaign.getTitle().equalsIgnoreCase(campaign.getTitle())
                        && existingCampaign.getDescription().equalsIgnoreCase(campaign.getDescription())) {
                    existingCampaign.setCampaignStatus(CampaignStatus.MUKERRER);
                    campaignRepository.save(existingCampaign);
                }
            }
        } else {
            switch (campaign.getCampaignCategory()) {
                case BE:
                case OE:
                case DIGER:
                    campaign.setCampaignStatus(CampaignStatus.ONAY_BEKLIYOR);
                    break;
                default:
                    campaign.setCampaignStatus(CampaignStatus.AKTIF);
                    break;
            }
        }
        campaignRepository.save(campaign);
        return modelMapperService.forResponse().map(campaign, CampaignDetailsResponse.class);
    }

    @Override
    public void updateCampaignStatus(UpdateCampaignStatusRequest updateCampaignStatusRequest) {
        campaignBusinessRules.checkIfIdExists(updateCampaignStatusRequest.getCampaignId());
        Campaign campaign = campaignRepository.findById(updateCampaignStatusRequest.getCampaignId()).get();
        campaign.setCampaignStatus(updateCampaignStatusRequest.getNewStatus());
        campaign.incrementStatusChangeCount();
        campaignRepository.save(campaign);
    }

    @Override
    public void deactivateCampaignStatus(DeactivateCampaignRequest deactivateCampaignRequest) {
        campaignBusinessRules.checkIfIdExists(deactivateCampaignRequest.getCampaignId());
        Campaign campaign = campaignRepository.findById(deactivateCampaignRequest.getCampaignId())
                .orElseThrow(() -> new RuntimeException("Kampanya Bulunamadı"));

        if (campaign.getCampaignStatus() == CampaignStatus.AKTIF ||
                campaign.getCampaignStatus() == CampaignStatus.ONAY_BEKLIYOR) {
            campaign.setCampaignStatus(CampaignStatus.DEAKTIF);
            campaignRepository.save(campaign);
        } else {
            throw new RuntimeException("Kampanya sadece Aktif veya Onay Bekliyor durumundayken Aktif Edilebilir");
        }
    }

    @Override
    public boolean checkDuplicate(CreateCampaignRequest createCampaignRequest) {
        List<Campaign> campaigns = campaignRepository.findAll();
        Map<String, List<Campaign>> groupedCampaigns = campaigns.stream()
                .collect(Collectors.groupingBy(c -> c.getCampaignCategory().name().toLowerCase() +
                        "_" + c.getTitle().toLowerCase() + "_" + c.getDescription().toLowerCase()));

        String key = createCampaignRequest.getCampaignCategory().name().toLowerCase()
                + "_" + createCampaignRequest.getTitle().toLowerCase() + "_" + createCampaignRequest.getDescription().toLowerCase();
        return groupedCampaigns.containsKey(key);
    }

    @Override
    public CampaignCountResponse countCampaign() {
        int activeCampaigns = campaignRepository.countByCampaignStatus(CampaignStatus.AKTIF);
        int deactiveCampaigns = campaignRepository.countByCampaignStatus(CampaignStatus.DEAKTIF);
        return new CampaignCountResponse(activeCampaigns, deactiveCampaigns);
    }

    @Override
    public void deleteCampaignById(int id) {
        campaignBusinessRules.checkIfIdExists(id);
        campaignRepository.deleteById(id);
    }
}
