package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;


public class App {


    public static void main(String[] args) throws Exception {
        LerCSV arquivo = new LerCSV("docs/grafos/br.csv");
        ArrayList<Cidade> listaCidades = arquivo.lerCidades();
        GrafoPonderado grafoCidades = new GrafoPonderado("GrafoCidades");
//        for (Cidade cidade : listaCidades) {
//            ArrayList<Cidade> cidadesMaisProximas = listaCidades.stream()
//                    .sorted((cidade1, cidade2) -> cidade1.distanciaEntreCidades(cidade2)).ma
//                    .limit(4)
//                    .toList();
//
//        }
    }


}
