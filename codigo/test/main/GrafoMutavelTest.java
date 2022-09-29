package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GrafoMutavelTest {
    GrafoMutavel teste = new GrafoMutavel("test");
    GrafoMutavel teste2 = new GrafoMutavel("teste2");

    @Test
    void add_existeVertice() {
        teste.addVertice(1);
        Assertions.assertNotEquals(null, teste.existeVertice(1));
    }

    @Test
    void addVerticeErro() {
        teste.addVertice(1);
        Assertions.assertEquals(false, teste.addVertice(1));
    }

    @Test
    void addArestaErro() {
        teste.addVertice(1);
        teste.addVertice(2);
        teste.addAresta(1, 2);

        Assertions.assertEquals(false, teste.addAresta(1, 2));
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
    void carregar() throws IOException {
       teste.carregar("teste");
       Assertions.assertEquals(3, teste.ordem());
    }

    @Test
    void salvar() throws IOException {
        teste.carregar("teste");
        teste.addVertice(4);

        teste.salvar("teste");

        teste2.carregar("teste");

        Assertions.assertEquals(4, teste2.ordem());
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

    @Test
    void grafoNaoCompleto() throws IOException {
        teste.carregar("teste");

        Assertions.assertEquals(false, teste.completo());
    }

    @Test
    void grafoCompleto() throws IOException {
        teste.carregar("completo");

        Assertions.assertEquals(true, teste.completo());
    }

    @Test
    void removeVertice() {
        teste.addVertice(1);
        teste.addVertice(2);

        teste.removeVertice(2);

        Assertions.assertEquals(null, teste.existeVertice(1));
    }

    
    @Test
    void delAresta() {
        teste.addVertice(1);
        teste.addVertice(2);

        teste.addAresta(1, 2);

        teste.delAresta(1, 2);

        Assertions.assertEquals(null, teste.existeAresta(1, 2));
    }

}
