package co.ceiba.vigilante.businesslogic;

import java.util.Date;

public interface VigilanteLogic {

	boolean restriccionIngreso(String placa);

	long obtenerDiferenciaHoras(Date fechaIngreso, Date fechaSalida);

	float obtenerValorPagar(long horas, int tipoVehiculo, int cilindraje);

	int obtenerConfisysCantidadLimiteVehiculos(int tipoVehiculo);

	String obtenerPropertiesByName(String name);

	void actualizarPropertiesByName(String nombre, String valor);

	String obtenerConfisysRestriccionPlaca();
	
}
