package ba.campaign.poc.api.campaign.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeactivateCampaignRequest {

    @NotNull(message = "Kampanya Id si Gerekli Alandır")
    private int campaignId;
}
