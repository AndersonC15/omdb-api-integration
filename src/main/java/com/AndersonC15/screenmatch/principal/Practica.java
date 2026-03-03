package com.AndersonC15.screenmatch.principal;

import java.util.List;
import java.util.Scanner;

public class Practica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        List<Double> preciosProductos = List.of(29.99, 49.50, 15.75, 99.99);
//        double totalGastado = preciosProductos.stream()
//                        .reduce(0.0, Double::sum);
//
//        double impuestoPuesto = (totalGastado *0.08)+totalGastado;
//        System.out.println("Valor total antes del impuesto: "+ totalGastado);
//
//        System.out.println("Valor total con impuesto de 8%: "+ impuestoPuesto);

        List<Double> notas = List.of(7.5, 8.0, 6.5, 9.0, 10.0);
        double notaBaja= notas.stream()
                .min(Double::compare).get();

        double notaAlta= notas.stream()
                .max(Double::compare).get();

        double totalNotas= notas.stream().mapToDouble(Double::doubleValue).sum();

        double media = totalNotas/notas.size();

        System.out.println("Notas de las evaluaciones: "+media);
        System.out.println("La nota mas baja es: "+ notaBaja);
        System.out.println("La nota mas alta es: "+ notaAlta);
    }
}
