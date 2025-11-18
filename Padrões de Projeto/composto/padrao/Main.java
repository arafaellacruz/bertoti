public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("Documentos");
        Folder sub = new Folder("Trabalho");
        sub.add(new File("Relatorio.pdf"));
        root.add(sub);
        root.add(new File("foto.png"));
        root.show();
    }
}
