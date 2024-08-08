package ba.campaign.poc.api.campaign.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignCountResponse {

    private int activeCampaigns;
    private int deactiveCampaigns;


}
