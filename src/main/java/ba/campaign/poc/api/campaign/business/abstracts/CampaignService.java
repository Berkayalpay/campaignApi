package ba.campaign.poc.api.campaign.business.abstracts;

import ba.campaign.poc.api.campaign.dtos.request.CreateCampaignRequest;
import ba.campaign.poc.api.campaign.dtos.request.DeactivateCampaignRequest;
import ba.campaign.poc.api.campaign.dtos.request.UpdateCampaignStatusRequest;
import ba.campaign.poc.api.campaign.dtos.response.CampaignCountResponse;
import ba.campaign.poc.api.campaign.dtos.response.CampaignDetailsResponse;

public interface CampaignService {

    //Burada Kampanya yaratırken tüm detayları görmek için KampanyaDetay tipi dönüşü yaptim.
    CampaignDetailsResponse addCampaign(CreateCampaignRequest createCampaignRequest);

    //Kampanya Durumu Güncellemesi
    void updateCampaignStatus(UpdateCampaignStatusRequest updateCampaignStatusRequest);

    //Kampanyaları deactivate etmek üzere olan şartımızı İŞ katmanında uygulucaz.
    void deactivateCampaignStatus(DeactivateCampaignRequest deactivateCampaignRequest);

    boolean checkDuplicate(CreateCampaignRequest createCampaignRequest);

    CampaignCountResponse countCampaign();

    void deleteCampaignById(int id);



}
