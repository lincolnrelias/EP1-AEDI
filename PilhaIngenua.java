public class PilhaIngenua{
    protected final int MAX = 1000000;
    protected Integer  [] pilha;
    PilhaIngenua(){pilha = new Integer[MAX];}

    void add(final int newElement){
        int i;
        for(i=0;pilha[i]!=null;i++);
            pilha[i]=newElement;
    }
    int remove(){
        int i;
        for(i=0;pilha[i]!=null;i++);
        int tmp=pilha[i-1];
        pilha[i-1]=null; 
        return tmp;
    }
    String formated(){
        String format="";
        for(int i=0;pilha[i]!=null;i++){
            format=format+pilha[i]+"\n";
        }
        return format;
    }
}