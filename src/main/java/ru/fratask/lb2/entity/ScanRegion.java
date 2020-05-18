package ru.fratask.lb2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScanRegion {

    private int length;
    private int offset;
    private ObjectContent objectContent;

    public int read(byte[] data) {
        return objectContent.read(offset, data);
    }

}
