package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrafoCompletoTest {

    GrafoCompleto teste = new GrafoCompleto("completo", 4);

    @Test
    void ordem() {
        Assertions.assertEquals(4, teste.ordem());
    }

    @Test
    void completo() {
        Assertions.assertEquals(true, teste.completo());
    }

    @Test
    void existeVertice() {
        teste.addVertice(1);
        Assertions.assertNotEquals(null, teste.existeVertice(1));
    }

    @Test
    void existeAresta() {
        teste.addVertice(1);
        teste.addVertice(2);

        teste.addAresta(1 ,2);

        Assertions.assertEquals(false, teste.addAresta(1, 2));
    }
}
