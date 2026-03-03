package com.AndersonC15.screenmatch.principal;

import com.AndersonC15.screenmatch.model.DatosEpisodio;
import com.AndersonC15.screenmatch.model.DatosSerie;
import com.AndersonC15.screenmatch.model.DatosTemporadas;
import com.AndersonC15.screenmatch.model.Episodio;
import com.AndersonC15.screenmatch.service.ConsumoAPI;
import com.AndersonC15.screenmatch.service.ConvertirDatos;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    private static final String URL_BASE = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=c27b3f4b";

    public void mostrarMenu() {
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar:");
        //Busca los datos generales de las series
        String nombreSerie = scanner.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = convertirDatos.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            var datosTemporadas = convertirDatos.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }


        //Mostrar solo el titulo de los episodios de las temporadas usando lambda
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        //Convertir todas las informaciones a una lista del tipo DatosEpisodio
        List<DatosEpisodio> datosEpisodio = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        //Top 5 episodios
        System.out.println("Top 5 episodios:");
        datosEpisodio.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primer Filtro: (N/A) "+ e))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .peek(e-> System.out.println("Segundo Filtro: (M>m): "+ e))
                .map(e-> e.titulo().toUpperCase())
                .peek(e-> System.out.println("Tercer Filtro Mayusculas: "+ e))
                .limit(5)
                .forEach(System.out::println);

        //Convirtiendo los datos a una lista de tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t->t.episodios().stream()
                .map(d->new Episodio(t.numero(),d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

        //Busqueda de episodios a partir de una fecha en especifico

        System.out.println("Por favor ingrese el año desde que quiere ver los episodios");
        var fecha= scanner.nextInt();
        scanner.nextLine();

        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e->e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
                .forEach(e -> System.out.println(
                        "Temporada: " +e.getTemporada()+
                                " Episodio: "+ e.getTitulo()+
                                " Fecha de Lanzamiento: "+ e.getFechaDeLanzamiento().format(dtf)
                ));

        //Buscar episodios por pedazo de titulo
        System.out.println("Por favor ingrese el titulo del episoodio que desea ver:");
        var pedazoTitulo =  scanner.nextLine();

        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e->e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
                .findFirst();
        if (episodioBuscado.isPresent()) {
            System.out.println("Episodio encontrado");
            System.out.println("Los datos son: "+ episodioBuscado.get());
        }else{
            System.out.println("Episodio no encontrado");
        }
    }
}