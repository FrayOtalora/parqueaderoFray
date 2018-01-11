package co.ceiba.vigilante.service;


import co.ceiba.vigilante.dominio.Parking;


public interface VigilanteService {

	void ingresarVehiculo(Parking parking);

	Parking retirarVehiculo(String placa);
	
	void registrarPagoVehiculo(Parking parking);
	
}
