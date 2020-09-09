import java.io.File; // Classe para ler arquivos
import java.io.FileWriter;//Classe para escrever arquivos
import java.io.FileNotFoundException;//Classe para lidar com erro de arquivo nao encontrado
import java.io.IOException; // Classe para poder lidar com erros de E/S
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class GeradorDeLogs {

  public static void main(final String[] args) throws IOException {
    final Scanner entrada = new Scanner(System.in);
    // Coloquei tudo num while para o teste ser mais fácil
    while (true) {
      System.out.println(
          "Digite o caminho do arquivo/diretorio a ser processado.(0 para sair do programa|1 para mudar estrutura)");
      // Recebe o nome do arquivo/diretorio a ser lido
      final String filePath = entrada.next();
      int structTypeNumber = 0;
      Estruturas struct;
      if (filePath.equals("0")) {// se for 0 fecha o programa
        break;
      } else if (filePath.equals("1")) {
        System.out.println("Escolha o tipo de estrutura(Padrao e Pilha Arranjo):");
        System.out.println("1-Pilha Ingenua");
        System.out.println("2-Lista Ligada");
        System.out.println("3-Pilha Arranjo");
        structTypeNumber = entrada.nextInt();
      }
      switch (structTypeNumber) {//Define o tipo de estrutura a ser usada
        case 1:
          struct = new PilhaIngenua();
        case 2:
          struct = new ListaLigada();
        default:
          struct = new PilhaArranjo();

      }
      final File dir = new File(filePath);// Cria uma instância de file para uso posterior
      final File[] directoryListing = dir.listFiles();// retorna um array com um arquivo de teste em cada posição
      long timeElapsed = 0;// variável usada para medir o tempo quando se itera por arquivos de um
                           // diretório
      long startTime = System.currentTimeMillis();// inicia o contador
      if (dir.isFile()) {// se foi passado um arquivo em vez de um diretório
        final String resultString = ReadFile(filePath, struct);// passa-se o caminho do arquivo e a estrutura a se
                                                         // utilizar(ListaLigada,PilhaIngenua,PilhaArranjo)
        // Se tiver erro de arquivo não encontrado, favor criar a pasta ArquivosSaida
        // na mesma pasta do programa
        try {
          final FileWriter writer = new FileWriter(
              "ArquivosSaida/" + dir.getName().substring(0, dir.getName().length() - 4) + "Saida.txt");
          writer.write(resultString);
          writer.close();
          System.out.println("o arquivo " + filePath.substring(0, filePath.length() - 4) + "Saida.txt"
              + " foi criado em ArquivosSaida");
        } catch (final IOException e) {
          System.out.println("Arquivo nao encontrado:" + e);
        }
        System.out.println(System.currentTimeMillis() - startTime);// printa o tempo gasto na operação
      } else if (directoryListing != null) {
        for (final File child : directoryListing) {
          startTime = System.currentTimeMillis();
          // armazena o tempo atual logo antes do método principal ser chamado
          // result String recebe a string a ser escrita no arquivo de saída
          final String filePathFormated = filePath + "/" + child.getName();
          Estruturas childStruct;
          try {
            childStruct = struct.getClass().getDeclaredConstructor().newInstance();//Cria uma nova estrutura de entrada para cada arquivo dentro da pasta
          } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
              | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
            childStruct = new PilhaArranjo();
          }
          final String resultString = ReadFile(filePathFormated, childStruct);
          Path path = Path.of("ArquivosSaida");
          if(Files.notExists(path)){// Se não existe o diretório ArquivosSaida na pasta do projeto
            Files.createDirectories(path);// O cria
          }
          try {
            final FileWriter writer = new FileWriter(
                "ArquivosSaida/" + child.getName().substring(0, child.getName().length() - 4) + "Saida.txt");
            writer.write(resultString);
            writer.close();
            System.out.println("o arquivo " + child.getName().substring(0, child.getName().length() - 4) + "Saida.txt"
                + " foi criado na pasta ArquivosSaida.");
          } catch (final IOException e) {
            System.out.println("Arquivo nao encontrado:" + e);
          }
          timeElapsed += (System.currentTimeMillis() - startTime);//Adiciona o tempo de processamento deste arquivo ao dos outros arquivos
        }
        System.out.println(timeElapsed);//printa tempo total de processamento
      }
    }
  }

  static String ReadFile(final String filePath, final Estruturas estrutura) {
    final Estruturas estruturaEntrada = estrutura;
    Estruturas estruturaSaida;//
    try {
      estruturaSaida = estrutura.getClass().getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e1) {
      System.out.println("Erro ao instanciar a classe da estrutura escolhida: "+e1.getMessage());
      estruturaSaida=new PilhaArranjo();//Se ocorrer algum erro ao identificar e instanciar a estrutura, é usado uma pilha arranjo para tentar rodar o programa.
    }
        try {
            final File myObj = new File(filePath);
            final Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              final String data = myReader.nextLine();
              if(data.equals("")){//se a linha for vazia
                estruturaSaida.add(estruturaEntrada.remove());//adiciona a lista de saídas o ultimo elemento da lista de entradas
              }else{
               estruturaEntrada.add(Integer.parseInt(data)); //se a linha n for vazia adiciona seu valor a lista de entradas
              }
              
            }
            myReader.close();
          } catch (final FileNotFoundException e) {
            System.out.println("Caminho para leitura do arquivo nao encontrado: "+e.getMessage());
            e.printStackTrace();
          }
          return estruturaSaida.formated();//retorna os dados da estrutura em forma de String para ser escrita
        }
}