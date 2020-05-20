package ru.fratask;

import ru.fratask.dao.InMemoryVirusDaoImpl;
import ru.fratask.dao.VirusDao;
import ru.fratask.entity.Signature;
import ru.fratask.entity.Virus;
import ru.fratask.gui.Window;
import ru.fratask.utils.VirusService;
import ru.fratask.utils.VirusServiceImpl;

public class Main {

    private static final VirusService virusService = new VirusServiceImpl();

    public static void main(String[] args) {
        virusService.addFromFile("C:\\Users\\Amir\\Documents\\IdeaProjects\\ZIotVPO\\src\\main\\resources\\viruses.txt");
        Window.getInstance().setVisible(true);

    }

}
