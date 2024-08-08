package ba.campaign.poc.api.campaign.entities;

import ba.campaign.poc.api.campaign.enumeration.CampaignCategory;
import ba.campaign.poc.api.campaign.enumeration.CampaignStatus;
import ba.campaign.poc.api.campaign.data.AbstractIntegerPKEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="campaign")
public class Campaign extends AbstractIntegerPKEntity {

    @Column(name="title", length = 500,
            updatable = false, columnDefinition = "VARCHAR(255) COLLATE \"tr-TR-x-icu\"")
    private String title;

    @Column(name = "description",
            updatable = false, columnDefinition = "VARCHAR(255) COLLATE \"tr-TR-x-icu\"")
    private String description;

    @Column(name = "is_duplicate")
    private boolean isDuplicate;

    @Enumerated(EnumType.STRING)
    @Column(name="campaign_status")
    private CampaignStatus campaignStatus;

    @Enumerated(EnumType.STRING)
    @Column(name="campaign_category")
    private CampaignCategory campaignCategory;

    @Column(name="status_change_count")
    private int statusChangeCount = 0;

    public void incrementStatusChangeCount(){
        this.statusChangeCount++;
    }
}
