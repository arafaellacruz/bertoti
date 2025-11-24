import java.util.ArrayList;
import java.util.List;

interface CalculoImpostoStrategy {
    double calcular(double valor);
}

class ImpostoICMS implements CalculoImpostoStrategy {
    @Override
    public double calcular(double valor) {
        return valor * 0.18; // 18% ICMS
    }
}

interface Observer {
    void atualizar(String mensagem);
}

class PainelResumo implements Observer {
    @Override
    public void atualizar(String mensagem) {
        System.out.println("-> Painel Resumo: " + mensagem);
    }
}

interface ItemOrcamento {
    double getValorBase();
    double getImposto();
    void setImpostoStrategy(CalculoImpostoStrategy strategy);
}

class Produto implements ItemOrcamento {
    private double valor;
    private CalculoImpostoStrategy impostoStrategy;
    
    private List<Observer> observers = new ArrayList<>();
    public void adicionarObserver(Observer o) { observers.add(o); }
    private void notificar(String msg) {
        for (Observer o : observers) o.atualizar(msg);
    }

    public Produto(double valor, CalculoImpostoStrategy strategy) {
        this.valor = valor;
        this.impostoStrategy = strategy;
    }

    public void setValor(double novoValor) {
        this.valor = novoValor;
        notificar("Valor do produto alterado para R$" + novoValor);
    }

    @Override
    public double getValorBase() { return valor; }
    @Override
    public double getImposto() { return impostoStrategy.calcular(valor); }
    @Override
    public void setImpostoStrategy(CalculoImpostoStrategy strategy) {
        this.impostoStrategy = strategy;
        notificar("Estratégia de imposto alterada.");
    }
}

class Projeto implements ItemOrcamento {
    private List<ItemOrcamento> itens = new ArrayList<>();

    public void adicionar(ItemOrcamento item) { itens.add(item); }

    @Override
    public double getValorBase() {
        return itens.stream().mapToDouble(ItemOrcamento::getValorBase).sum();
    }
    
    @Override
    public double getImposto() {
        return itens.stream().mapToDouble(ItemOrcamento::getImposto).sum();
    }

    @Override
    public void setImpostoStrategy(CalculoImpostoStrategy strategy) {
        for (ItemOrcamento item : itens) {
            item.setImpostoStrategy(strategy);
        }
    }
}

public class SistemaDeComponentesReativosDemo {
    public static void main(String[] args) {
        CalculoImpostoStrategy icms = new ImpostoICMS();
        PainelResumo painel = new PainelResumo();

        Produto licenca = new Produto(500.0, icms);
        licenca.adicionarObserver(painel); 

        Projeto moduloA = new Projeto();
        moduloA.adicionar(new Produto(200.0, icms));
        moduloA.adicionar(new Produto(300.0, icms));

        Projeto orcamentoPrincipal = new Projeto();
        orcamentoPrincipal.adicionar(licenca);
        orcamentoPrincipal.adicionar(moduloA);

        System.out.println("--- 1. Cálculo Inicial ---");
        System.out.println("Total Base: R$" + orcamentoPrincipal.getValorBase());
        System.out.println("Total Imposto: R$" + orcamentoPrincipal.getImposto());

        System.out.println("\n--- 2. Alteração do Comportamento ---");
        CalculoImpostoStrategy impostoZero = valor -> 0.0;
        
        orcamentoPrincipal.setImpostoStrategy(impostoZero); 
        System.out.println("Novo Total Imposto: R$" + orcamentoPrincipal.getImposto());
        
        System.out.println("\n--- 3. Reatividade (Observer) ---");
        licenca.setValor(750.0); 
    }
}