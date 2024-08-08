package ba.campaign.poc.api.campaign.dtos.request;

import ba.campaign.poc.api.campaign.enumeration.CampaignCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateCampaignRequest {
    @NotBlank(message = "Başlık Zorunlu Alandır")
    @Size(min=10 , max=50)
    @Pattern(regexp = "^[a-zA-Z0-9çÇğĞıİöÖşŞüÜ].*", message = "Name must start with a letter (including Turkish characters) or a digit")
    private String title;

    @NotBlank(message="Açıklama Zorunlu Alandır")
    @Size(min=20,max=200)
    private String description;

    @NotNull(message = "Kategori Zorunlu Alandır")
    private CampaignCategory campaignCategory;



}
