package main;

import java.util.*;
import java.util.stream.Collectors;


public class App {


    public static void main(String[] args) throws Exception {
        LerCSV arquivo = new LerCSV("docs/grafos/br.csv");
        ArrayList<Cidade> listaCidades = arquivo.lerCidades();
        GrafoPonderado grafoCidades = new GrafoPonderado("GrafoCidades");
        for (Cidade cidade : listaCidades) {
            HashMap<Double, Cidade> cidadeDistancias = new HashMap<>();
            ArrayList<Double> distancias = new ArrayList<>();
            listaCidades.forEach(c -> {
                double distancia = cidade.distanciaEntreCidades(c);
                if(distancia != 0) {
                    cidadeDistancias.put(distancia, c);
                    distancias.add(distancia);
                }
            });

            List<Cidade> cidadeProximas = new ArrayList<>();

            distancias.stream().sorted().limit(4).toList()
                    .forEach(ds -> cidadeProximas.add(cidadeDistancias.get(ds)));

            System.out.println("cidade" + cidade);
            cidadeProximas.forEach(System.out::println);
        }
    }


}
