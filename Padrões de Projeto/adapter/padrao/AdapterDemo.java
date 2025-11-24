interface TomadaTresPinos {
    void ligarTomadaDeTresPinos();
}

class EletrodomesticoDoisPinos {
    public void conectar() {
        System.out.println("Eletrodoméstico: Conexão de dois pinos estabelecida.");
    }
}

class AdaptadorDeTomada implements TomadaTresPinos {
    private EletrodomesticoDoisPinos eletrodomestico;

    public AdaptadorDeTomada(EletrodomesticoDoisPinos eletrodomestico) {
        this.eletrodomestico = eletrodomestico;
    }

    @Override
    public void ligarTomadaDeTresPinos() {
        System.out.println("Adaptador: Inserindo dois pinos na tomada de três pinos.");
        eletrodomestico.conectar(); 
        System.out.println("Adaptador: Eletrodoméstico ligado com sucesso.");
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        EletrodomesticoDoisPinos geladeira = new EletrodomesticoDoisPinos();
        
        TomadaTresPinos adaptador = new AdaptadorDeTomada(geladeira);
        
        System.out.println("Cliente ligando o aparelho...");
        adaptador.ligarTomadaDeTresPinos();
    }
}