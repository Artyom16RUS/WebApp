package ru.itpark.service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileService {
    private final String uploadPath;

    public FileService() throws IOException { //создаем путь через переменную среды если не создан
        uploadPath = System.getenv("UPLOAD_PATH");//TODO ? что находим
        Files.createDirectories(Paths.get(uploadPath));
    }

    public void readFile(String id, ServletOutputStream sos) throws IOException { //отправка файла клиенту по id через поток OutputStream
        var path = Paths.get(uploadPath).resolve(id);
        Files.copy(path, sos);
    }

    public String writeFile(Part part) throws IOException { //TODO ? что то записываем
        var id = UUID.randomUUID().toString();
        part.write(Paths.get(uploadPath).resolve(id).toString());
        return id;
    }

}
