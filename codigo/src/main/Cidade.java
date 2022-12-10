package main;

import static java.lang.Math.*;

public class Cidade extends Vertice {
    private final double latitude;
    private final double longitude;
    private final String estado;

    private final String nome;


    public Cidade(String nome, double latitude, double longitude, String estado) {
        super(nome + ", " + estado);
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.estado = estado;
    }

    public double distanciaEntreCidades(Cidade cidade2) {
        if ((this.latitude == cidade2.latitude) && (this.longitude == cidade2.longitude)) {
            return 0;
        } else {
            double theta = this.longitude - cidade2.longitude;
            double dist = sin(toRadians(this.latitude)) * sin(toRadians(cidade2.latitude)) + cos(toRadians(this.latitude)) * cos(toRadians(cidade2.latitude)) * cos(toRadians(theta));
            dist = acos(dist);
            dist = toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return (dist);
        }
    }

    public String getName() {
        return this.nome;
    }
}
