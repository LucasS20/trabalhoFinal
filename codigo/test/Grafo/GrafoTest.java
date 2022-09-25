package Grafo;

import main.Grafo;
import org.junit.jupiter.api.Test;

public class GrafoTest {
    Grafo teste = new Grafo("test");

    @Test
    void addVertice() {
        teste.addVertice(1);

    }
}
