package main;

import java.util.*;
import java.util.stream.Collectors;


public class App {


    public static void main(String[] args) throws Exception {
        LerCSV arquivo = new LerCSV("docs/grafos/br.csv");
        ArrayList<Cidade> listaCidades = arquivo.lerCidades();
        GrafoPonderado grafoCidades = new GrafoPonderado("GrafoCidades");
        for (Cidade cidade : listaCidades) {
            HashMap<Double, Cidade> cidadeProximas = new HashMap<>();
            ArrayList<Double> distancias = new ArrayList<>();
            listaCidades.forEach(c -> {
                double distancia = cidade.distanciaEntreCidades(c);
                cidadeProximas.put(distancia, c);
                distancias.add(distancia);
            });

            List<Double> dSorted = distancias.stream().sorted().limit(4).toList();

            List<Cidade> cSorted = new ArrayList<>();
            List<Cidade> finalCSorted = cSorted;
            dSorted.forEach(ds -> finalCSorted.add(cidadeProximas.get(ds)));

            finalCSorted.forEach(System.out::println);
        }
    }


}
