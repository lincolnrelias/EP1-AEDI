public class ListaLigada implements Estruturas {
    Node cabeca=null;
    static class Node{
     int dados;
     Node prox;

     Node(int _dados){dados=_dados;prox=null;}//Construtor
    }

    public void add( int _dados) { 
        // Cria um novo nó com os dados passados
        Node no = new Node(_dados); 
  
        // Se a lista está vazia
        // adicionar um nó e defini-lo como cabeça
            no.prox=cabeca;
            cabeca=no;
    } 
    //Método que deleta o ultimo elemento da lista
    public  int remove() throws IllegalStateException{
        if(cabeca==null){
            throw new IllegalStateException("lista vazia");
        }
        Node primeiro = cabeca;
        cabeca = cabeca.prox;
        primeiro.prox=null;

        return primeiro.dados;
    }
  
    // Método que retorna a String a ser escrita no arquivo de saída
    public  String formated() 
    { 
        Node noAtual = cabeca; 
        String format="";
        // Itera pela lista 
        while (noAtual != null) { 
            // Concatena os dados do nó atual
            // a string de saida
            format=noAtual.dados+"\n"+format;
   
            // Vai para o próximo nó
            noAtual = noAtual.prox; 
        } 
        return format;
    } 

}