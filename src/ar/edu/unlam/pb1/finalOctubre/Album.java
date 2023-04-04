package ar.edu.unlam.pb1.finalOctubre;

public class Album {
	private static String selecciones[] = { "Qatar", "Ecuador", "Senegal", "Paises Bajos", "Inglaterra", "Iran", "EEUU",
			"Gales", "Argentina", "Arabia Saudita", "Mexico", "Polonia", "Francia", "Australia", "Dinamarca", "Tunez",
			"España", "Costa Rica", "Alemania", "Japón", "Bélgica", "Canadá", "Marruecos", "Croacia", "Brasil",
			"Serbia", "Suiza", "Camerún", "Portugal", "Ghana", "Uruguay", "Corea del Sur" };

	private static Figurita figuritasDisponibles[] = new Figurita[704];
	private Figurita figuritasActuales[];

	private int cantidadDeFiguritasPresentesEnAlbumActualSinRepetirse;

	/*
	 * El constructor debe generar las condiciones necesarias para garantizar el
	 * correcto funcionamiento de la app
	 */
	public Album() {
		this.figuritasActuales = new Figurita[1000];
		this.cantidadDeFiguritasPresentesEnAlbumActualSinRepetirse = 0;
	}

	/*
	 * Por cada pa�s debe generar 22 c�digos de manera autom�tica por ejemplo QAT1,
	 * URU12, COS10
	 */
	public static void inicializarCodigosDeFiguritas() {
		int contadorSeleccion = 0;
		int contadorFigurita = 1;
		String codigo = "";

		for (int i = 0; i < figuritasDisponibles.length; i++) {
			codigo = selecciones[contadorSeleccion].toUpperCase().substring(0, 3) + contadorFigurita;
			figuritasDisponibles[i] = new Figurita(codigo);
			figuritasDisponibles[i].setSeleccion(selecciones[contadorSeleccion]);
			contadorFigurita++;
			if (contadorFigurita == 23 && contadorSeleccion < selecciones.length - 1) {
				contadorSeleccion++;
				contadorFigurita = 1;
			}
		}
	}

	/*
	 * En funci�n del c�digo de figurita, se deben actualizar los datos de la misma
	 * en figuritasDisponibles
	 */
	public static void actualizarDatosFigurita(String codigo, char grupo, String nombreJugador, double valor) {
		Figurita figurita = getFigurita(codigo);

		figurita.setGrupo(grupo);
		figurita.setNombreJugador(nombreJugador);
		figurita.setValor(valor);
	}

	/*
	 * En funci�n del c�digo de figurita, devuelve la figurita de
	 * figuritasDisponibles
	 */
	public static Figurita getFigurita(String codigo) {

		for (int i = 0; i < figuritasDisponibles.length; i++) {
			if (figuritasDisponibles[i] != null && figuritasDisponibles[i].getCodigo().equals(codigo)) {
				return figuritasDisponibles[i];
			}
		}
		return null;
	}

	/*
	 * Se debe calcular y devolver 5 c�digos de figurita de manera aleatoria.
	 */
	public Figurita[] comprarSobre() {

		Figurita sobre[] = new Figurita[5];
		int indiceAleatorio = 0;

		for (int i = 0; i < sobre.length; i++) {
			indiceAleatorio = (int) (Math.random() * figuritasDisponibles.length - 1);
			sobre[i] = figuritasDisponibles[indiceAleatorio];
		}

		return sobre;
	}

	/*
	 * Agrega una nueva figurita al array figuritasActuales
	 */
	public void agregarFigurita(Figurita nueva) {
		for (int i = 0; i < this.figuritasActuales.length; i++) {
			if (this.figuritasActuales[i] == null && nueva != null) {
				figuritasActuales[i] = nueva;
				break;
			}
		}

	}

	/*
	 * Debe ordenar el array figuritasActuales
	 */
	public void ordenarFiguritasActuales() {
		Figurita auxiliar;

		for (int i = 0; i < this.figuritasActuales.length; i++) {

			for (int j = 0; j < this.figuritasActuales.length - 1; j++) {
				if (this.figuritasActuales[j] != null && this.figuritasActuales[j + 1] != null) {
					if (this.figuritasActuales[j].getCodigo()
							.compareTo(this.figuritasActuales[j + 1].getCodigo()) > this.figuritasActuales[j + 1]
									.getCodigo().compareTo(this.figuritasActuales[j].getCodigo())) {

						auxiliar = this.figuritasActuales[j];
						this.figuritasActuales[j] = this.figuritasActuales[j + 1];
						this.figuritasActuales[j + 1] = auxiliar;

					}

				}

			}
		}
	}

	/*
	 * Debe verificar que todas las figuritas disponibles est�n presentes al menos
	 * una vez en las figuritas actuales
	 */
	public boolean elAlbumEstaCompleto() {
		int repeticiones = 0;

		for (int i = 0; i < this.figuritasActuales.length; i++) {
			for (int j = i+1; j < figuritasActuales.length-1; j++) {
				if (this.figuritasActuales[i] != null && this.figuritasActuales[j] != null && this.figuritasActuales[i] == figuritasActuales[j]) {
					repeticiones++;
				}
				
			}
		}

		this.cantidadDeFiguritasPresentesEnAlbumActualSinRepetirse = this.getCantidadDeFiguritasPresentesEnAlbumActual()-repeticiones;

		if (cantidadDeFiguritasPresentesEnAlbumActualSinRepetirse == figuritasDisponibles.length)
			return true;
		else
			return false;

	}
	
	/*
	 * Debe calcular el porcentaje de figuritas del album que est� completo. Para
	 * esto se debe basar en la informaci�n de las figuritasDisponibles en relaci�n
	 * a las figuritasActuales que se tiene en el album.
	 */
	public double calcularPorcentajeCompletado() {
		double porcentaje = 0;
		
		porcentaje = (double)this.cantidadDeFiguritasPresentesEnAlbumActualSinRepetirse * 100 / figuritasDisponibles.length;

		return porcentaje;
	}

	public Figurita[] getFiguritasActuales() {
		return figuritasActuales;
	}

	public static Figurita[] getFiguritasDisponibles() {
		return figuritasDisponibles;
	}

	public int getCantidadDeFiguritasPresentesEnAlbumActual() {
		int cantidadDeFiguritas=0;
		
		for(int i=0; i<this.figuritasActuales.length; i++) {
			if(this.figuritasActuales[i]!=null) {
				cantidadDeFiguritas++;
			}
		}
		return cantidadDeFiguritas;
	}
	


}
