package co.ceiba.vigilante.repository;

import java.util.Date;

import co.ceiba.vigilante.dominio.Parking;

public interface VigilanteRepository {

	boolean ingresarVehiculo(String placa, Date fechaIngreso, int tipoVehiculo, int cilindraje);

	Parking retirarVehiculo(String placa);

	boolean registrarPagoVehiculo(int id);
	
	String obtenerConfisysByName(String nombre);
	
}