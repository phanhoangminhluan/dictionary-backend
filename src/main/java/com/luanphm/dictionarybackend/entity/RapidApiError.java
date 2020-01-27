package com.luanphm.dictionarybackend.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RapidApiError {

    private boolean success;
    private String message;

}
