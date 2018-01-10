package co.ceiba.vigilante.service;


import co.ceiba.vigilante.dominio.Parking;
import co.ceiba.vigilante.dominio.Vehiculo;


public interface VigilanteService {

	void ingresarVehiculo(Vehiculo vehiculo) throws Exception;

	Parking retirarVehiculo(Vehiculo vehiculo) throws Exception;
	
	void registrarPagoVehiculo(Parking parking) throws Exception;
	
}
