package com.workintech.s18d1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BurgerErrorResponse {
    private String massage;

    public BurgerErrorResponse(String massage) {
        this.massage = massage;
    }

    public String getMessage() {
        return massage;
    }
}
