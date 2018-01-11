package co.ceiba.vigilante.testdatabuilder;

import java.util.Date;

import co.ceiba.vigilante.dominio.Parking;
import co.ceiba.vigilante.dominio.Vehiculo;

public class ParkingTestDataBuilder {

	private int id;
	private Vehiculo vehiculo;
	private Date fechaIngreso;
	private Date fechaRetiro;
	private float valorPago;
	
	
	public ParkingTestDataBuilder() {
		super();
	}
	
	
	public Parking build() {
		return new Parking();
	}

	
	

	
	
}
