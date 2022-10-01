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
            this.addVertice(i);
        }

        for(int i = 1; i <= ordem; i++) {
            for (int j = 1; j <= ordem; j++ ) {
                if( i != j )
                    addAresta(i,j);
            }
        }
    }

    private void addVertice(int id){
        Vertice novo = new Vertice(id);
        this.vertices.add(id, novo);
    }

    private void addAresta(int origem, int destino){
        boolean adicionou = false;
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);

        if(saida != null && chegada != null) {
            adicionou = saida.addAresta(1, destino);
        }

    }

}
