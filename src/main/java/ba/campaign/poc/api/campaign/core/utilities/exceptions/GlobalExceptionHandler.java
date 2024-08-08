package ba.campaign.poc.api.campaign.core.utilities.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetails> handleBusinessException(BusinessException ex) {
        log.error("BusinessException occurred: {}", ex.getMessage());
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage());
        return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<ProblemDetails> handleInvalidCategoryException(InvalidCategoryException ex) {
        log.error("Geçersiz Kategori Hatası occurred: {}", ex.getMessage());
        ProblemDetails problemDetails = new ProblemDetails(ex.getMessage());
        return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetails> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        if (mostSpecificCause instanceof InvalidFormatException) {
            String errorMessage = "Geçersiz Kategori: Lütfen geçerli bir kategori giriniz. Kabul edilen kategoriler: KE, BE, OE, DIGER, GE.";
            log.error("InvalidFormatException occurred: {}", mostSpecificCause.getMessage());
            ProblemDetails problemDetails = new ProblemDetails(errorMessage);
            return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
        }
        log.error("HttpMessageNotReadableException occurred: {}", ex.getMessage());
        ProblemDetails problemDetails = new ProblemDetails("Beklenmedik bir hata oluştu.");
        return new ResponseEntity<>(problemDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handleGlobalException(Exception ex) {
        log.error("Unexpected exception occurred: {}", ex.getMessage());
        ProblemDetails problemDetails = new ProblemDetails("Beklenmedik bir hata oluştu.");
        return new ResponseEntity<>(problemDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
