package com.mycompany.propertymanagement.exception;

        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private String message;

    public BusinessException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private List<ErrorModel> errors;

    public BusinessException(List<ErrorModel>errors){
        this.errors = errors;
    }
}
