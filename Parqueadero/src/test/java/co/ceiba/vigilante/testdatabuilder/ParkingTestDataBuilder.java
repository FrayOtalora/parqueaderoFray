package co.ceiba.vigilante.testdatabuilder;

import java.util.Date;

import co.ceiba.vigilante.dominio.Parking;

public class ParkingTestDataBuilder {

	private int id;
	private String placa;
	private int tipoVehiculo;
	private int cilindraje;
	private Date fechaIngreso;
	private Date fechaRetiro;
	private float valorPago;

	public ParkingTestDataBuilder() {
		super();
		this.placa = "123456";
		this.tipoVehiculo = 0;
		this.cilindraje = 0;
	}

	public ParkingTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public ParkingTestDataBuilder withCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public ParkingTestDataBuilder withTipoVehiculo(int tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public Parking build() {
		return new Parking(placa, tipoVehiculo, cilindraje);
	}

}
