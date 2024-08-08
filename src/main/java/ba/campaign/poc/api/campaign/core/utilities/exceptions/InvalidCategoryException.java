package ba.campaign.poc.api.campaign.core.utilities.exceptions;


public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(String message) {
        super(message);
    }
} //Geçersiz kategori oldugunda fırlatılan özel exceptiondır.
//Runtime sınıfından türetilmiş olup message parametresi alır.
