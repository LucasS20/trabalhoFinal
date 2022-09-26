package Grafo;

import main.Grafo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrafoTest {
    Grafo teste = new Grafo("test");

    @Test
    void add_existeVertice() {
        teste.addVertice(1);
        Assertions.assertNotEquals(null, teste.existeVertice(1));
    }

    @Test
    void add_existeAresta() {
        teste.addVertice(1);
        teste.addVertice(2);
        teste.addAresta(1, 2);
        Assertions.assertNotEquals(null, teste.existeAresta(1, 2));
    }

    @Test
    void ordem() {
        teste.addVertice(1);
        Assertions.assertEquals(1, teste.ordem());
    }

    @Test
    void tamanho() {
        teste.addVertice(1);
        teste.addVertice(2);
        teste.addVertice(3);
        teste.addVertice(4);

        teste.addAresta(1, 2);
        teste.addAresta(2, 1);

        teste.addAresta(2, 3);
        teste.addAresta(3, 2);

        teste.addAresta(3, 4);
        teste.addAresta(4, 3);

        teste.addAresta(4, 1);
        teste.addAresta(1, 4);

        Assertions.assertEquals(8, teste.tamanho());
    }

}
