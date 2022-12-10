package main;

public class GrafoCompleto extends Grafo {
    private int ordem;


    /**
     * Construtor. Cria um grafo vazio com capacidade para MAX_VERTICES
     *
     * @param nome
     */
    public GrafoCompleto(String nome, int ordem) {
        super(nome);

        this.ordem = ordem;

        for(int i = 1; i <= ordem; i++ ) {
            this.addVertice(String.valueOf(i));
        }

        for(int i = 1; i <= ordem; i++) {
            for (int j = 1; j <= ordem; j++ ) {
                if( i != j )
                    addAresta(String.valueOf(i), String.valueOf(j));
            }
        }
    }

    private void addVertice(String nome){
        Vertice novo = new Vertice(nome);
        this.vertices.add(nome, novo);
    }

    private void addAresta(String origem, String destino){
        boolean adicionou = false;
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);

        if(saida != null && chegada != null) {
            adicionou = saida.addAresta(1, chegada);
        }

    }

}
