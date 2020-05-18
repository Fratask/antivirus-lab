package ru.fratask.lb1;

import ru.fratask.lb1.dao.InMemoryVirusDaoImpl;
import ru.fratask.lb1.dao.VirusDao;
import ru.fratask.lb1.entity.Signature;
import ru.fratask.lb1.entity.Virus;

public class Main {

    static VirusDao virusDao = new InMemoryVirusDaoImpl();

    public static void main(String[] args) {
        virusDao.create(Virus.builder().name("Name1").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());
        virusDao.create(Virus.builder().name("Name2").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());
        virusDao.create(Virus.builder().name("Name3").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());
        virusDao.create(Virus.builder().name("Name4").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());
        virusDao.create(Virus.builder().name("Name5").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());
        virusDao.create(Virus.builder().name("Name6").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());
        virusDao.create(Virus.builder().name("Name7").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());
        virusDao.create(Virus.builder().name("Name8").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());
        virusDao.create(Virus.builder().name("Name9").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());
        virusDao.create(Virus.builder().name("Name10").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());
        virusDao.create(Virus.builder().name("Name11").minSignatureByteOffset(10L).maxSignatureByteOffset(20L).signature(new Signature()).build());

        System.out.println(virusDao.findAll());
        System.out.println(virusDao.findVirusById(2L));
        System.out.println(virusDao.findVirusByName("Name4"));

        virusDao.deleteVirusByName("Name4");
        virusDao.updateVirusById(Virus.builder().id(2L).build());
        virusDao.updateVirusByName(Virus.builder().name("Name3").build());
        System.out.println(virusDao.findAll());


    }

}
