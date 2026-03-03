    package com.AndersonC15.screenmatch.principal;

    import java.util.Arrays;
    import java.util.List;

    public class EjemploStreams {

        public void MuestraEjemplo(){
            List<String> nombres = Arrays.asList("Brenda", "Luis", "Anderson", "Kiara", "Ares");
            nombres.stream().sorted().limit(4).filter(n -> n.startsWith("A")).forEach(System.out::println);

        }
    }
