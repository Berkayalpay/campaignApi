package ba.campaign.poc.api.campaign.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;


@Getter
@Setter
@MappedSuperclass
public class AbstractIntegerPKEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @AccessType(AccessType.Type.FIELD)
    private int id;




}
