package ar.edu.unlam.pb1.finalOctubre;

/* Tomar como referenca a la correccion del profe Juani (Mi apunte)
 * 
 * Album
 * 	Album : Bien
 * ---------------------------------
 * 	inicializarCodigosDeFiguritas : 
 * 		Profe: Estas salteando la seleccion ubicada en la posicion cero y el esle  if con el break no parece necesario pero el resto de la logica, muy bien.
 *  	¿Arreglado? Listo, lo arreglé.
 *  ---------------------------------
 * 	actualizarDatosFigurita: bien
 * ----------------------------------
 * 	getFigurita: bien
 * -----------------------------------
 * 	comprarSobre: bien
 * ----------------------------------
 * 	agregarFigurita: 
 * 		Profe: No. una vez agregas la figurita tenes que cortar la recorrida, sino se llena el array con la primera
 * figurita.
 * 		¿Arreglado? Si, yo por acostumbrarme con return, que la pifie.
 * -------------------------------------------------------------------
 * #	ordenarFiguritasActuales: Vacio
 *	¿Arreglado? Si completado
 * 
 * # 	elAlbumEstaCompleto: Vacio
 *  ¿Arreglado? Realizado pero dudando si cumplo con la consigna..
 *  
 * # 	calcularPorcentajeCompletado: Vacio..
 *   ¿Arreglado? Realizado pero dudando si cumplo con la consigna..

 * 	
 * Qatar2022
 * 	comprarSobre: Bien
 * 	visualizarFiguritasActuales: Bien
 * 
 * */
import java.util.Scanner;

public class Qatar2022 {
	
	private static final int ACTUALIZAR_DATOS_FIGURITA = 1, 
							COMPRAR_SOBRE = 2, 
							VISUALIZAR_FIGURITAS_ACTUALES = 3,
							CALCULAR_PORCENTAJE_DE_COMPLETADO = 4,
							SALIR = 9;

	public static void main(String[] args) {

		Album actual = new Album();
		Album.inicializarCodigosDeFiguritas();

		int opcionIngresada;
		Scanner teclado = new Scanner(System.in);

		System.out.println("Bienvenido album de figuritas Panini Virtual");
		
		mostrarLasFiguritasDisponiblesGeneradas(); //No va esto pero lo agrego para ver como quedaron las figuritas

		do {
			mostrarMenu();
			opcionIngresada = teclado.nextInt();
			actual = determinarAccionARealizar(actual, opcionIngresada, teclado);
		} while (!actual.elAlbumEstaCompleto());
	}

	private static void mostrarLasFiguritasDisponiblesGeneradas() {
		for(int i=0; i<Album.getFiguritasDisponibles().length; i++) {
			if(Album.getFiguritasDisponibles()[i]!=null)
			System.out.println(Album.getFiguritasDisponibles()[i].toString());
		}
	}
	
	private static void mostrarMenu() {
		System.out.println("************************");
		System.out.println("Menu de opciones\n");
		System.out.println(ACTUALIZAR_DATOS_FIGURITA + " - Actualizar datos de la figurita");
		System.out.println(COMPRAR_SOBRE + " - Comprar sobre ");
		System.out.println(VISUALIZAR_FIGURITAS_ACTUALES + " - Visualizar figuritas actuales");
		System.out.println(CALCULAR_PORCENTAJE_DE_COMPLETADO + " - Calcular porcentaje de completado");
		System.out.println(SALIR + " - Salir");
		System.out.println("************************");
	}
	
	private static Album determinarAccionARealizar(Album actual, int opcionIngresada, Scanner teclado) {

		switch(opcionIngresada) {
		case ACTUALIZAR_DATOS_FIGURITA:
			actualizardatosFigurita(teclado, actual);
			break;
		case COMPRAR_SOBRE:
			comprarSobre(teclado, actual);
			break;
		case VISUALIZAR_FIGURITAS_ACTUALES:
			visualizarFiguritasActuales(teclado, actual);
			break;
		case CALCULAR_PORCENTAJE_DE_COMPLETADO:
			calcularElPorcentajeDeCompletado(teclado, actual);
			break;
		case SALIR:
			break;
		}
		return actual;
	}
	
	private static void actualizardatosFigurita(Scanner teclado, Album actual) {
		String codigo;
		char grupo;
		String seleccion;
		String nombreJugador;
		double valor;
		Figurita aActualizar;

		System.out.println("***************");
		System.out.println("Actualizar datos de figurita");
		System.out.println("***************"); 

		System.out.println("Ingrese el codigo a actualizar");
		codigo = teclado.next();
		aActualizar = Album.getFigurita(codigo);
		
		System.out.println("Selección: " + aActualizar.getSeleccion());
		
		System.out.println("Ingrese el grupo al que pertenece la selecci�n");
		grupo = teclado.next().charAt(0);
		System.out.println("Ingrese el nombre del jugador");
		nombreJugador = teclado.next();
		System.out.println("Ingrese el valor");
		valor = teclado.nextDouble();

		Album.actualizarDatosFigurita(codigo, grupo, nombreJugador, valor);
	}

	private static void comprarSobre(Scanner teclado, Album actual) {
		Figurita compradas[] = actual.comprarSobre();
		
		System.out.println("Las figuritas son");

		/*
		 * Mostrar las figuritas obtenidas
		 */
		for(int i=0;  i<compradas.length; i++) {
			System.out.println(compradas[i].toString());
		}
		
		/* 
		 * Por cada figuria comprada, agregarlas al album actual actual.agregarFigurita(compradas[i]);
		 */
		for(int i=0; i<compradas.length; i++) {
			actual.agregarFigurita(compradas[i]);
		}
		
	}
	
	private static void visualizarFiguritasActuales(Scanner teclado, Album actual) {
		/*
		 * Se deben mostrar las figuritas que posee el usuario de manera ordenada.
		 */
		actual.ordenarFiguritasActuales();
		
		Figurita[] figuritasActuales = actual.getFiguritasActuales();
		
		for(int i=0; i<figuritasActuales.length; i++) {
			if(figuritasActuales[i]!=null) {
				System.out.println(figuritasActuales[i].toString());
			}	
		}
		
	}
	
	private static void calcularElPorcentajeDeCompletado(Scanner teclado, Album actual) {
		System.out.println(actual.getCantidadDeFiguritasPresentesEnAlbumActual());
		System.out.println("El album está completo en un " + String.format("%.2f", actual.calcularPorcentajeCompletado()) + "%");
	}


}
