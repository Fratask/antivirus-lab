package ru.fratask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Signature {

    private int length;
    private byte[] data;

    public Signature(byte[] data) {
        this.data = data;
    }
}
