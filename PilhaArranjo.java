public class PilhaArranjo implements Estruturas{
        protected final int MAX = 1000000;
        protected int  [] pilha;
        protected int lastElementPos=-1;
    
        PilhaArranjo(){pilha = new int[MAX];}
    
        public void add(int newElement) {
            pilha[lastElementPos + 1] = newElement;
            lastElementPos++;
        }
        public int remove(){
            final int removedValue = pilha[lastElementPos];
            pilha[lastElementPos]=0;
            lastElementPos--;
            return removedValue;
        }
        public String formated(){
            String format="";
            for(int i=0;i<=lastElementPos;i++){
                format=format+pilha[i]+"\n";
            }
            return format;
        }
}
