package ba.campaign.poc.api.campaign.enumeration;

    public enum CampaignStatus {

        ONAY_BEKLIYOR("Onay Bekliyor"),
        AKTIF("Aktif"),
        MUKERRER("Mükerrer"),
        DEAKTIF("Deaktif");


        private final String displayName;

        CampaignStatus(String displayName){
            this.displayName=displayName;
        }

        @Override
        public String toString() {
            return this.displayName;
        }


    }

