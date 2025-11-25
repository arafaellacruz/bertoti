interface TomadaTresPinos {
    void ligarTomadaDeTresPinos();
}

class EletrodomesticoDoisPinos {
    public void conectar() {
        System.out.println("Eletrodoméstico: Conexão de dois pinos estabelecida.");
    }
    
    public void realizarManutencao() {
        System.out.println("Eletrodoméstico: Realizando manutenção interna específica do modelo.");
    }
}

class Adapter extends EletrodomesticoDoisPinos implements TomadaTresPinos {
    @Override
    public void ligarTomadaDeTresPinos() {
        System.out.println("Adaptador de Herança: Adaptando e ligando.");
        super.conectar(); 
        System.out.println("Adaptador de Herança: Ligado com sucesso.");
    }
}

public class AdapterAntipattern {
    public static void main(String[] args) {
        TomadaTresPinos adaptadorRuim = new Adapter();
        
        System.out.println("--- 1. Uso conforme o Padrão Target ---");
        adaptadorRuim.ligarTomadaDeTresPinos(); 
        
        System.out.println("\n--- 2. Uso do método herdado (Vazamento) ---");
        
        if (adaptadorRuim instanceof Adapter) {
            Adapter adaptadorConcreto = (Adapter) adaptadorRuim;
            adaptadorConcreto.realizarManutencao(); 
        }   
    }
}