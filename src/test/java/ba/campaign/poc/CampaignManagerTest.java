package ba.campaign.poc;

import ba.campaign.poc.api.campaign.business.concretes.CampaignManager;
import ba.campaign.poc.api.campaign.core.utilities.mappers.ModelMapperService;
import ba.campaign.poc.api.campaign.dataAccess.abstracts.CampaignRepository;
import ba.campaign.poc.api.campaign.dtos.request.CreateCampaignRequest;
import ba.campaign.poc.api.campaign.dtos.request.UpdateCampaignStatusRequest;
import ba.campaign.poc.api.campaign.dtos.response.CampaignDetailsResponse;
import ba.campaign.poc.api.campaign.entities.Campaign;
import ba.campaign.poc.api.campaign.enumeration.CampaignCategory;
import ba.campaign.poc.api.campaign.enumeration.CampaignStatus;
import ba.campaign.poc.api.campaign.rules.CampaignBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignManagerTest {

    //Gerçek sınıflar yerine sahte sınıf orneklerı olusturuyoruz.
    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private CampaignBusinessRules campaignBusinessRules;

    //Mocklanan nesneleri campaignManager sınıfına inject ediyoruz.
    @InjectMocks
    private CampaignManager campaignManager;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        lenient().when(modelMapperService.forRequest()).thenReturn(modelMapper);
        lenient().when(modelMapperService.forResponse()).thenReturn(modelMapper);
    }



    @Test
    void testAddCampaign() {
        //addCampaign metoduna geçilecek olan parametreleri veriyoruz.
        CreateCampaignRequest request = new CreateCampaignRequest();
        request.setTitle("Test Campaign");
        request.setDescription("Test Description");
        request.setCampaignCategory(CampaignCategory.BE);

        //createCampaign nesnesindeki aynı verilerle set ediyoruz.
        //Bu nesne model mapperin dondurecegı ve repositorynin kaydedecegı kampanyayı temsil eder.
        Campaign campaign = new Campaign();
        campaign.setTitle(request.getTitle());
        campaign.setDescription(request.getDescription());
        campaign.setCampaignCategory(request.getCampaignCategory());
        campaign.setCampaignStatus(CampaignStatus.ONAY_BEKLIYOR);

        CampaignDetailsResponse campaignDetailsResponse = new CampaignDetailsResponse();
        campaignDetailsResponse.setTitle(campaign.getTitle());
        campaignDetailsResponse.setDescription(campaign.getDescription());
        campaignDetailsResponse.setCampaignCategory(campaign.getCampaignCategory());

        when(modelMapper.map(any(CreateCampaignRequest.class), eq(Campaign.class))).thenReturn(campaign);
        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);
        when(campaignRepository.findAll()).thenReturn(Collections.emptyList());
        when(modelMapper.map(any(Campaign.class), eq(CampaignDetailsResponse.class))).thenReturn(campaignDetailsResponse);

        CampaignDetailsResponse result = campaignManager.addCampaign(request);

        assertNotNull(result);
        verify(campaignRepository, times(1)).save(any(Campaign.class));
        verify(campaignRepository, times(1)).findAll();
        verify(campaignBusinessRules, times(1)).checkIfCampaignCategoryExists(any(CampaignCategory.class));
    }

    @Test
    void testUpdateCampaignStatus() {
        UpdateCampaignStatusRequest request = new UpdateCampaignStatusRequest();
        request.setCampaignId(1);
        request.setNewStatus(CampaignStatus.AKTIF);

        Campaign campaign = new Campaign();
        campaign.setId(request.getCampaignId());
        campaign.setCampaignStatus(CampaignStatus.ONAY_BEKLIYOR);

        when(campaignRepository.findById(anyInt())).thenReturn(Optional.of(campaign));

        campaignManager.updateCampaignStatus(request);

        assertEquals(CampaignStatus.AKTIF, campaign.getCampaignStatus());
        verify(campaignBusinessRules, times(1)).checkIfIdExists(anyInt());
        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }





}






