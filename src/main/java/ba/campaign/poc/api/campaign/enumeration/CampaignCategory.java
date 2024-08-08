package ba.campaign.poc.api.campaign.enumeration;

public enum CampaignCategory {
    BE("Bireysel Eğitim"),
    GE("Grup Eğitimleri"),
    KE("Kurumsal Eğitimler"),
    OE("Online Eğitimler"),
    DIGER("Diğer");

    private final String displayName;

    CampaignCategory(String displayName){
        this.displayName=displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

}