## Engenharia de Software - Giuliano Bertoti

### Atividade 1: 08/08/2024

We see three critical differences between programming and software engineering: time, scale, and the trade-offs at play. On a software engineering project, engineers need to be more concerned with the passage of time and the eventual need for change. In a software engineering organization, we need to be more concerned about scale and efficiency, both for the software we produce as well as for the organization that is producing it. Finally, as software engineers, we are asked to make more complex decisions with higher-stakes outcomes, often based on imprecise estimates of time and growth.

-> O que é Engenharia de Software?
Engenharia de Software é o processo de planejar, desenhar e aplicar um software pensando na escalabilidade e eficiência dele a curto e longo prazo, ou seja, criando um desenvolvimento onde atendemos as funções atuais e também, conforme ele cresce em tamanho e complexidades, de forma que não afete a funcionabilidade e desempenho do sistema.

### Atividade  2: 12/08/2024 
Dê 3 exemplos de trade-offs:

1. Usabilidade: é a medida de quão fácil e intuitivo é para os usuários interagirem com um sistema ou aplicação, garantindo eficiência, eficácia e satisfação na realização de tarefas.
2. Desempenho: é a eficiência com que um sistema executa suas funções, medido em velocidade, uso de recursos e capacidade de lidar com cargas de trabalho.
3. Confiabilidade: é a capacidade de um sistema de funcionar corretamente e sem falhas em condições normais de operação durante um período específico.

### Atividade 3: 19/08/2024 

#### Arquivo: Animal.java

```java
package atividade3;

// Criando classe publica e definindo os atributos com segurança privada
public class Animal {
	private String raca;
	private String nome;
	
	// Criando construtor publico que atribui valor aos atributos da classe
	public Animal (String raca, String nome) {
		this.raca = raca;
		this.nome = nome;
	}

	// Criando metodos de acesso e modificação
	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
		
}
```

#### Arquivo: Petshop.java
```java
  package atividade3;
  
  import java.util.LinkedList;
  import java.util.List;
  
  // Criando classe Petshop atribuindo um atributo do tipo lista do objeto Animal
  public class Petshop {
      // LinkedList é como se fosse um array de objetos
      private List<Animal> animais = new LinkedList<Animal>();
      
      // Criando um método para cadastrar um animal ao petshop
      public void cadastrarAnimal(Animal animal) {
          animais.add(animal);
      }
      
      // Criando método para buscar um animal na lista existente de animais do petshop
      public List<Animal> buscarAnimalPeloNome(String nome) {
          List<Animal> animaisEncontrados = new LinkedList<Animal>();
          
          // Verifica se o nome do animal é igual ao nome fornecido e o adiciona à lista de animais encontrados
          for(Animal animal : animais) {
              if(animal.getNome().equals(nome)) {
                  animaisEncontrados.add(animal);
              }
          }
          return animaisEncontrados;
      }
      
      /* Criando método para retornar uma lista com todos os animais cadastrados */
      public List<Animal> getAnimais() {
          return animais;
      }
  }
```

#### Arquivo: TestAnimal.java
```java
package atividade3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class TestAnimal {

	@Test
	void test() {
		// Criando um objeto petshop do tipo Petshop
		Petshop petshop = new Petshop();
		
		// Criando objetos do tipo Animal
		Animal darlo = new Animal("Anta", "Darlo");
		Animal yllebasi = new Animal("Gata", "Yllebasi");
		
		// Usando o metodo cadastrarAnimal para adicionais 2 objetos ao petshop
		petshop.cadastrarAnimal(darlo);
		petshop.cadastrarAnimal(yllebasi);
		
		// Verificando se ao usar o metodo getAnimais retorna o tamanho 2 (quantidade de itens)
		assertEquals(petshop.getAnimais().size(), 2);
		
		// Usando metodo buscarAnimalPeloNome para buscar um animal pelo nome 'Darlo', e esta verificando se o Darlo tem a mesma raca do objeto darlo
		List<Animal> animal = petshop.buscarAnimalPeloNome("Darlo");
		assertEquals(animal.get(0).getRaca(), darlo.getRaca());
	}

}

```

### Atividade  4: Diagrama de Classes UML

![Classes UMl](https://github.com/user-attachments/assets/4236955a-bba2-4aa3-9398-5f9873ae4757)



### Atividade  5: Assistente JAVA integrado com modelos de linguagem
[Clique aqui para acessar o site](https://github.com/arafaellacruz/bertoti/tree/main/Eng%20SW/LoLBuildBuddy)


