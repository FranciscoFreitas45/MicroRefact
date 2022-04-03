package br.com.fatecmogidascruzes.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.file.data.FileDAO;
import br.com.fatecmogidascruzes.file.File;
@Service
public class FileEventService {

@Autowired
 private FileDAO filedao;


}