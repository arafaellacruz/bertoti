interface TomadaTresPinos {
    void ligarTomadaDeTresPinos();
}

class EletrodomesticoDoisPinos {
    public void conectar() {
        System.out.println("Eletrodoméstico: Conexão de dois pinos estabelecida.");
    }
    
    public void validarAterramento() {
        System.out.println("Eletrodoméstico: Verificando aterramento específico do pino de serviço.");
    }
}

interface TomadaVazandoAbstracao extends TomadaTresPinos {
    void verificarDetalhesEspeciais(); 
}

class AdaptadorRuim implements TomadaVazandoAbstracao {
    private EletrodomesticoDoisPinos eletrodomestico; 

    public AdaptadorRuim(EletrodomesticoDoisPinos eletrodomestico) {
        this.eletrodomestico = eletrodomestico;
    }

    @Override
    public void ligarTomadaDeTresPinos() {
        eletrodomestico.conectar();
    }
    
    @Override
    public void verificarDetalhesEspeciais() {
        eletrodomestico.validarAterramento();
    }
}

public class LeakyAbstractionDemo {
    public static void main(String[] args) {
        EletrodomesticoDoisPinos lavadora = new EletrodomesticoDoisPinos();
        
        TomadaVazandoAbstracao adaptadorRuim = new AdaptadorRuim(lavadora);
        
        System.out.println("Cliente usa o método principal...");
        adaptadorRuim.ligarTomadaDeTresPinos();
        
        System.out.println("\nCliente usa o método vazado, forçando o acoplamento...");
        adaptadorRuim.verificarDetalhesEspeciais(); 
    }
}