package ru.fratask.lb1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Signature {

    private Long length;
    private byte[] data;

    public Signature(byte[] data) {
        this.data = data;
    }
}
