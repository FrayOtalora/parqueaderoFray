package co.ceiba.parqueaderoRest.service;


import co.ceiba.parqueaderoRest.dominio.Parking;
import co.ceiba.parqueaderoRest.dominio.Vehiculo;


public interface ParqueaderoService {

	boolean ingresarVehiculo(Parking parking) throws Exception;

	Parking retirarVehiculo(Vehiculo vehiculo) throws Exception;
	
	boolean registrarPagoVehiculo(Parking parking) throws Exception;
	
}
