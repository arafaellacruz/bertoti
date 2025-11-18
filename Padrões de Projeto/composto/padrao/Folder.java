import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemItem {
    private String name;
    private List<FileSystemItem> items = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemItem item) {
        items.add(item);
    }

    public void show() {
        System.out.println("📁 Pasta: " + name);
        for (FileSystemItem i : items) {
            i.show();
        }
    }
}
