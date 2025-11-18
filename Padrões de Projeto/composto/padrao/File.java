public class File implements FileSystemItem {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("- Arquivo: " + name);
    }
}
