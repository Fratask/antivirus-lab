package ru.fratask.utils;

import ru.fratask.dao.InMemoryVirusDaoImpl;
import ru.fratask.dao.VirusDao;
import ru.fratask.entity.Signature;
import ru.fratask.entity.Virus;
import ru.fratask.exception.AntivirusException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VirusServiceImpl implements VirusService {

    private final VirusDao virusDao = InMemoryVirusDaoImpl.getInstance();

    @Override
    public int addFromFile(String path) {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new AntivirusException(e);
        }

        byte[] data = new byte[100];
        int countOfViruses = 0;
        try {
            while (inputStream.read(data) != -1) {

                //name|minByOff|maxByteOffset   |sLen|signature data block
                //ssss|00000000|0000000000000000|0000|0000000000000000000000000000000000000000000000000000000000000000
                Signature signature = Signature.builder()
                        .length(getIntFromData(data, 31, 4))
                        .data(getBytesFromData(data, 36, 64))
                        .build();
                Virus newVirus = Virus.builder()
                        .name(getStringFromData(data, 0, 4))
                        .minSignatureByteOffset(getIntFromData(data, 5, 8))
                        .maxSignatureByteOffset(getIntFromData(data, 14, 16))
                        .signature(signature)
                        .build();
                virusDao.create(newVirus);
                System.out.println("Virus created: " + newVirus.toString());
                countOfViruses++;
                byte[] end = new byte[2];
                inputStream.read(end);
            }
        } catch (IOException e) {
            throw new AntivirusException(e);
        }
        return countOfViruses;

    }

    private int getIntFromData(byte[] data, int offset, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < offset+length; i++) {
            sb.append(getCharFromByte(data[i]));
        }
        return Integer.parseInt(sb.toString());
    }

    private String getStringFromData(byte[] data, int offset, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < offset+length; i++) {
            sb.append(getCharFromByte(data[i]));
        }
        return sb.toString();
    }

    private byte[] getBytesFromData(byte[] data, int offset, int length) {
        byte[] result = new byte[length];
        if (length - offset >= 0) System.arraycopy(data, offset, result, offset - offset, length - offset);
        return result;
    }

    private char getCharFromByte(byte data) {
        return (char) data;
    }
}
