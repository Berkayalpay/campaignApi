package ba.campaign.poc.api.campaign.dataAccess.abstracts;

import ba.campaign.poc.api.campaign.entities.Campaign;
import ba.campaign.poc.api.campaign.enumeration.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

    @Query("SELECT COUNT(c) FROM Campaign c WHERE c.campaignStatus = :status")
    int countByCampaignStatus(@Param("status") CampaignStatus status);

    boolean existsById(int id);
}
