package leituraimg;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class LeituraImg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File arquivo = new File("H01.pgm");
        try (InputStream in = new FileInputStream(arquivo)) {
            Scanner imgEnt = new Scanner(in);
            //LEITURA DO CABELÇALHO
            imgEnt.nextLine();
            imgEnt.nextLine();
            imgEnt.nextLine();
            int numCol = Integer.parseInt(imgEnt.next()); // leitura do número de colunas
            int numLin = Integer.parseInt(imgEnt.next()); // leitura do númeor de linhas
            imgEnt.next(); // leitura do maior valor de pixel

            //LEITURA DO DADOS DA IMAGEM E CARREGAMENTO DA MATRIZ
            int[][] matrizImg = new int[numLin][numCol];
            for (int lin = 0; lin < numLin; lin++) {
                for (int col = 0; col < numCol; col++) {

                    //matrizImg[lin][col] = Integer.parseInt(imgEnt.next());
                    matrizImg[lin][col] = imgEnt.nextInt();
                    

                }
            }

//           for (int lin = 0; lin < matrizImg.length; lin++) {
//                for (int col = 0; col < matrizImg[lin].length; col++) {
//                    System.out.print(matrizImg[lin][col] + " ");
//                }
//                System.out.println();
//            }
            // GRAVANDO IMAGEM PGM
            try (FileWriter out = new FileWriter("H01limiar50.pgm")) {
                BufferedWriter bw = new BufferedWriter(out);
                //ESCREVENDO O CABEÇALHO DA IAMGEM
                bw.write("P2");
                bw.newLine();
                bw.write("# Criado pelo Grupo");
                bw.newLine();
                bw.write(numCol + " " + numLin);
                bw.newLine();
                bw.write("255");
                bw.newLine();
                //ESCREVENDO OS DADOS DA IMAGEM
                for (int lin = 0; lin < matrizImg.length; lin++) {
                    for (int col = 0; col < matrizImg[lin].length; col++) {
                        if (matrizImg[lin][col] >= 50){
                        matrizImg[lin][col] = 255;
                    }else{
                        matrizImg[lin][col] = 0;
                    }

                        bw.write(matrizImg[lin][col] + " ");

                    }
                    bw.write("\n");
                }
                bw.flush();

            } catch (IOException ex) {
                System.out.println("ERRO AO SALVAR O ARQUIVO");
            }

        } catch (IOException ex) {
            System.out.println("ERRO AO ABRIR O ARQUIVO");
        }

    }

}
