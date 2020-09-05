import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
public class GeradorDeLogs{
    
    public static void main(String[] args) throws IOException{
      Scanner entrada = new Scanner(System.in); 
    while(true){
    System.out.println("Digite o caminho(relativo) do arquivo a ser processado.");
    String filePath = entrada.next();
    long startTime = System.currentTimeMillis();
    String resultString = ReadFile("entradas/"+filePath).formated();
    try{
      FileWriter writer = new FileWriter("C:/Users/Public/Desktop/"+filePath.substring(0, filePath.length()-4)+"Saida.txt");
     writer.write(resultString);     
     writer.close();
     System.out.println("o arquivo foi criado no seu desktop(windows) se houver problemas mude o caminho de criação do arquivo");
    } catch (IOException e){ 
      System.out.println("Arquivo não encontrado:"+e);
    } 
    System.out.println(System.currentTimeMillis()-startTime);
  } 
    }
    static PilhaIngenua ReadFile(String filePath){
        PilhaIngenua pilhaEntrada = new PilhaIngenua();
        PilhaIngenua pilhaSaida = new PilhaIngenua();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              if(data.equals("")){
                pilhaSaida.add(pilhaEntrada.remove());
              }else{
               pilhaEntrada.add(Integer.parseInt(data)); 
              }
              
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          return pilhaSaida;
    }
}