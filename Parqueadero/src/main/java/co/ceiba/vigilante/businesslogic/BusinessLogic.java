package co.ceiba.vigilante.businesslogic;

import java.util.Date;

public interface BusinessLogic {

	boolean restriccionIngreso(String placa);

	long obtenerDiferenciaHoras(Date fechaIngreso, Date fechaSalida);

	float obtenerValorPagar(long horas, int tipoVehiculo, int cilindraje);

	int cantidadLimiteVehiculos(int tipoVehiculo);

	String obtenerPropertiesByName(String name);

	void actualizarPropertiesByName(String nombre, String valor);

}
