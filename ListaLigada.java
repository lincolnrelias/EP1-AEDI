public class ListaLigada  {
    No cabeca=null;
    No cauda=null;

    static class No{
     int dados;
     No prox;

     No(int _dados){dados=_dados;prox=null;}//Construtor
    }

    public void add( int _dados) { 
        // Cria um novo nó com os dados passados
        No no = new No(_dados); 
  
        // Se a lista está vazia
        // adicionar um nó e defini-lo como cabeça
        if (cabeca == null) { 
            no.prox=cabeca;
            cabeca=no;
            cauda=no;
        } 
        else { 
            no.prox=null;
            cauda.prox = no;
            cauda=no;
        } 
    } 
    //Método que deleta o ultimo elemento da lista
    public  int remove() throws IllegalStateException{
        if(cabeca==null){
            throw new IllegalStateException("lista vazia");
        }
        No ultimo = cabeca;
        No penultimo = null;
        if(ultimo.prox==null && ultimo!=null){
            int returnedValue = ultimo.dados;
            cabeca=null;
            cauda=null;
            return returnedValue;
           }
        while(ultimo.prox!=null){
            penultimo = ultimo;
            ultimo=ultimo.prox;
        }
        
        
        penultimo.prox=null;
        cauda=penultimo;
        return ultimo.dados;
    }
  
    // Método que retorna a String a ser escrita no arquivo de saída
    public  String formated() 
    { 
        No noAtual = cabeca; 
        String format="";
        // Itera pela lista 
        while (noAtual != null) { 
            // Concatena os dados do nó atual
            // a string de saida
            format=format+noAtual.dados+"\n";
   
            // Vai para o próximo nó
            noAtual = noAtual.prox; 
        } 
        return format;
    } 

}