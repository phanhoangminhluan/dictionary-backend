package com.luanphm.dictionarybackend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {
    private boolean isSuccess;
    private String messages;
    private Object body;

    public static final boolean SUCCESS = true;
    public static final boolean FAIL = false;
    public static final String WORK_SUCCESSFULLY = "Success";
    public static final String WORD_NOT_FOUND = "There is no definition of this word";
    public static final String WORD_IS_EXISTED = "This word has been existed.";
    public static final Object EMPTY_BODY = null;
    public static ResponseDTO generateResponseObject(Boolean isSuccess, String messages, Object body) {
        return ResponseDTO.builder()
                    .isSuccess(isSuccess)
                    .messages(messages)
                    .body(body)
                .build();
    }
}
