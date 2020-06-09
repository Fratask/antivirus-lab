package ru.fratask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Virus {

    private String name;
    private int length;
    private byte[] sequence;
    private int minOffset;
    private int maxOffset;

}
