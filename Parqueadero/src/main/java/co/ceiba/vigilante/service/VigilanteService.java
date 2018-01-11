package co.ceiba.vigilante.service;


import co.ceiba.vigilante.dominio.Parking;
import co.ceiba.vigilante.dominio.Vehiculo;


public interface VigilanteService {

	void ingresarVehiculo(Vehiculo vehiculo);

	Parking retirarVehiculo(String placa);
	
	void registrarPagoVehiculo(Parking parking);
	
}
