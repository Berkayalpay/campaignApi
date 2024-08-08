package ba.campaign.poc.api.campaign.dtos.response;

import ba.campaign.poc.api.campaign.enumeration.CampaignCategory;
import ba.campaign.poc.api.campaign.enumeration.CampaignStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDetailsResponse {
    private int id;
    private String title;
    private String description;
    private CampaignStatus campaignStatus;
    private CampaignCategory campaignCategory;
    private boolean isDuplicate;




}
