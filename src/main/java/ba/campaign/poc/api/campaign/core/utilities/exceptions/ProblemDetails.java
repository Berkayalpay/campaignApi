package ba.campaign.poc.api.campaign.core.utilities.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDetails { //Hata DETAYLARINI saklamak ıcın olan sınıf.
    String message;
}

