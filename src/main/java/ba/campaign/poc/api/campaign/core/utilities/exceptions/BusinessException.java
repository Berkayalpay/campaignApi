package ba.campaign.poc.api.campaign.core.utilities.exceptions;

public class BusinessException extends  RuntimeException{

    //Constructor ile Runtime exceptiondan gelen özellikleri kullanmak adına.
    public BusinessException(String message) {
        super(message); //super base sınıf demek.
    }

}
