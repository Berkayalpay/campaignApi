package ba.campaign.poc.api.campaign.rules;

import ba.campaign.poc.api.campaign.core.utilities.exceptions.BusinessException;
import ba.campaign.poc.api.campaign.core.utilities.exceptions.InvalidCategoryException;
import ba.campaign.poc.api.campaign.dataAccess.abstracts.CampaignRepository;
import ba.campaign.poc.api.campaign.enumeration.CampaignCategory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CampaignBusinessRules {
    private final CampaignRepository campaignRepository;

    public void checkIfIdExists(int id) {
        if (!this.campaignRepository.existsById(id)) {
            throw new BusinessException("Aradığınız ID bulunamadi");
        }
    }

    public void checkIfCampaignCategoryExists(CampaignCategory campaignCategory) {
        boolean isValidCategory = false;
        for (CampaignCategory validCategory : CampaignCategory.values()) {
            if (validCategory.equals(campaignCategory)) {
                isValidCategory = true;
                break;
            }
        }
        if (!isValidCategory) {
            log.error("Geçersiz Kategori: {}", campaignCategory);
            throw new InvalidCategoryException("Geçersiz Kategori: Lütfen geçerli bir kategori giriniz. " + campaignCategory);
        } else {
            log.info("Geçerli Kategori: {}", campaignCategory);
        }
    }
}
