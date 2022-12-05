package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LerCSV {

    String caminho;

    LerCSV(String caminho) {
        this.caminho = caminho;
    }

    public ArrayList<Cidade> lerCidades() {
        ArrayList<Cidade> listaCidades = new ArrayList<>();
        final int NOME = 0;
        final int LATITUDE = 1;
        final int LONGITUDE = 2;
        final int ESTADO = 5;
        BufferedReader br = null;
        String linha;
        String csvDivisor = ",";
        try {
            br = new BufferedReader(new FileReader(caminho));
            while ((linha = br.readLine()) != null) {
                String[] dadosLinha = linha.split(csvDivisor);
                if(!dadosLinha[NOME].equals("city")) {
                    Cidade cidade = new Cidade(dadosLinha[NOME], Double.parseDouble(dadosLinha[LATITUDE]), Double.parseDouble(dadosLinha[LONGITUDE]), dadosLinha[ESTADO]);
                    listaCidades.add(cidade);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listaCidades;
    }
}
