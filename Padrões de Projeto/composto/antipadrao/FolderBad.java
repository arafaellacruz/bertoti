import java.util.ArrayList;
import java.util.List;

public class FolderBad {
    private List<File> files = new ArrayList<>();

    public void add(File file) {
        files.add(file);
    }

    public void show() {
        for (File f : files) {
            System.out.println("Arquivo: " + f.getName());
        }
    }

    public static void main(String[] args) {
        FolderBad folder = new FolderBad();
        folder.add(new File("Relatorio.pdf"));
        folder.add(new File("foto.png"));
        folder.show();
    }
}
