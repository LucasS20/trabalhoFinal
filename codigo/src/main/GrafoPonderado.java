package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GrafoPonderado extends GrafoMutavel {

    public GrafoPonderado(String nome) {
        super(nome);
        
    }
    
    @Override
    public void carregar(String nome) throws IOException {
//        String path = PATH + nome + ".csv";
//        File arquivo = new File(path);
//
//        FileReader file = new FileReader(arquivo);
//        BufferedReader reader = new BufferedReader(file);
//
//
//        String line;
//        line = reader.readLine();
//        int id;
//        int idOrigem = 0;
//        boolean firstLine = true;
//        String[] splitMatrix;
//
//        while(line != null) {
//            splitMatrix = line.split(";");
//            boolean firstValue = true;
//
//            if (firstLine) {
//                for (String matrix : splitMatrix) {
//                    if(!matrix.equals(" ")) {
//                        id = Integer.parseInt(matrix);
//                        this.addVertice(id);
//                    }
//                }
//                firstLine = false;
//            } else {
//                for (String matrix : splitMatrix) {
//                    if (firstValue) {
//                        if(!matrix.equals(" ")) {
//                            idOrigem = Integer.parseInt(matrix);
//                            firstValue = false;
//                        }
//                    } else if (matrix.equals("1")) {
//                        id = Integer.parseInt(matrix);
//                        this.addAresta(idOrigem, id);
//                    }
//                }
//
//                line = reader.readLine();
//            }
//        }
//        reader.close();
//        file.close();
    }

    
    public boolean addAresta(String origem, String destino, double peso){
        boolean adicionou = false;
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);

        if(saida != null && chegada != null) {
            adicionou = saida.addAresta(peso, chegada);
        }

        return adicionou;
    }
}

