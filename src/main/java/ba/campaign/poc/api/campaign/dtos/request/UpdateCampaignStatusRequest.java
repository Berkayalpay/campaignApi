package ba.campaign.poc.api.campaign.dtos.request;

import ba.campaign.poc.api.campaign.enumeration.CampaignStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCampaignStatusRequest {

    //Kampanyanın Sadece Durum Bilgisi Güncellenicek Olup diğer alanlar guncellenmıcek şekilde varsayılıyor.
    @NotNull(message="Kampanya Id si Zorunlu alandir")
    private int campaignId;

    @NotNull(message = "Kampanya Statüsü Zorunlu Alandır")
    private CampaignStatus newStatus;

}
