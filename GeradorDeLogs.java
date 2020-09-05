import java.io.File;  // Classe para ler arquivos
import java.io.FileWriter;//Classe para escrever arquivos
import java.io.FileNotFoundException;//Classe para lidar com erro de arquivo nao encontrado
import java.io.IOException;  // Classe para poder lidar com erros de E/S
import java.util.Scanner;

public class GeradorDeLogs {

  public static void main(String[] args) throws IOException {
    Scanner entrada = new Scanner(System.in);
    // Coloquei tudo num while para o teste ser mais fácil
    while(true){
    System.out.println("Digite o caminho do arquivo/diretório a ser processado.");
    // Recebe o nome do arquivo/diretorio a ser lido
    String filePath = entrada.next();

    File dir = new File(filePath);//Cria uma instância de file para uso posterior
    File[] directoryListing = dir.listFiles();//retorna um array com um arquivo de teste em cada posição
    long timeElapsed = 0;//variável usada para medir o tempo quando se itera por arquivos de um diretório
    long startTime = System.currentTimeMillis();//inicia o contador
    if (dir.isFile()) {//se foi passado um arquivo em vez de um diretório
      String resultString = ReadFilePilhaIngenua(filePath);//passa-se o caminho do arquivo e a estrutura a se utilizar
      try {
        System.out.println(dir.getName());
        FileWriter writer = new FileWriter(
            "ArquivosSaida/" + dir.getName().substring(0, dir.getName().length() - 4) + "Saida.txt");
        writer.write(resultString);
        writer.close();
        System.out.println(
            "o arquivo " + filePath.substring(0, filePath.length() - 4) + "Saida.txt" + " foi criado em ArquivosSaida");
      } catch (IOException e) {
        System.out.println("Arquivo não encontrado:" + e);
      }
      System.out.println(System.currentTimeMillis() - startTime);
    } else if (directoryListing != null) {
      for (File child : directoryListing) {
        // armazena o tempo atual logo antes do método principal ser chamado
        // result String receve a string a ser escrita no arquivo de saída
        String filePathFormated = filePath + "/" + child.getName();
        String resultString = ReadFilePilhaIngenua(filePathFormated);
        try {
          FileWriter writer = new FileWriter(
              "ArquivosSaida/" + child.getName().substring(0, child.getName().length() - 4) + "Saida.txt");
          writer.write(resultString);
          writer.close();
          System.out.println("o arquivo " + child.getName().substring(0, child.getName().length() - 4) + "Saida.txt"
              + " foi criado na pasta ArquivosSaida.");
        } catch (IOException e) {
          System.out.println("Arquivo não encontrado:" + e);
        }
        timeElapsed += (System.currentTimeMillis() - startTime);
      }
      System.out.println(timeElapsed);
    }
  }
  }

  static String ReadFilePilhaIngenua(String filePath) {
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
          return pilhaSaida.formated();
        }
  
        static String ReadFilePilhaArranjo(String filePath) {
          PilhaArranjo pilhaEntrada = new PilhaArranjo();
          PilhaArranjo pilhaSaida = new PilhaArranjo();
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
                return pilhaSaida.formated();
              }
static String ReadFileLista(String filePath) {
  ListaLigada listaEntrada = new ListaLigada();
    ListaLigada listaSaida = new ListaLigada();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              if(data.equals("")){
                listaSaida.add(listaEntrada.remove()); 
              }else{
                listaEntrada.add(Integer.parseInt(data));
              }
              
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          return listaSaida.formated();
        }

}