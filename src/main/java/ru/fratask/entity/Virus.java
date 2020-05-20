package ru.fratask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Virus {

    private Long id;
    private String name;
    private Signature signature;
    private int minSignatureByteOffset;
    private int maxSignatureByteOffset;
}
