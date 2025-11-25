package com.lukasaldivia.microserviciocuenta.model;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String mensaje;
    private T data;

    public ApiResponse(String mensaje, T data) {
        this.mensaje = mensaje;
        this.data = data;
    }

}
