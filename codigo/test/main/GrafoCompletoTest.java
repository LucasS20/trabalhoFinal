package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrafoCompletoTest {

    GrafoCompleto teste = new GrafoCompleto("completo", 4);

    @Test
    void ordem() {
        Assertions.assertEquals(4, teste.ordem());
    }

//    @Test
//    void completo() {
//        Assertions.assertEquals(true, teste.completo());
//    }

    @Test
    void existeVertice() {
        Assertions.assertNotEquals(null, teste.existeVertice("1"));
    }

//    @Test
//    void existeAresta() {
//        Assertions.assertEquals(false, teste.existeAresta(1, 2));
//    }
}
