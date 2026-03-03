package com.AndersonC15.screenmatch;

import com.AndersonC15.screenmatch.model.DatosEpisodio;
import com.AndersonC15.screenmatch.model.DatosSerie;
import com.AndersonC15.screenmatch.model.DatosTemporadas;
import com.AndersonC15.screenmatch.principal.EjemploStreams;
import com.AndersonC15.screenmatch.principal.Principal;
import com.AndersonC15.screenmatch.service.ConsumoAPI;
import com.AndersonC15.screenmatch.service.ConvertirDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.mostrarMenu();

//		EjemploStreams ejemploStreams = new EjemploStreams();
//		ejemploStreams.MuestraEjemplo();

	}
}
