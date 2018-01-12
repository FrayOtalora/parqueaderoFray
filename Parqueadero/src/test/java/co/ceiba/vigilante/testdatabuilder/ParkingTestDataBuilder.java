package co.ceiba.vigilante.testdatabuilder;

import java.util.Date;

import co.ceiba.vigilante.dominio.Parking;

public class ParkingTestDataBuilder {

	private int id;
	private String placa;
	private int tipoVehiculo;
	private int cilindraje;
	private Date fechaIngreso;
	private Date fechaSalida;
	private float valorPago;

	public ParkingTestDataBuilder() {
		super();
		this.placa = "123456";
		this.tipoVehiculo = 0;
		this.cilindraje = 0;
		
	}
	
	public ParkingTestDataBuilder withId(int id) {
		this.id=id;
		return this;
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
	
	public ParkingTestDataBuilder withFechaSalida(Date fechaSalida) {
		this.fechaSalida=fechaSalida;
		return this;
	}
	
	public ParkingTestDataBuilder withValorPago(float valor) {
		this.valorPago=valor;
		return this;
	}
	
	public ParkingTestDataBuilder withFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso=fechaIngreso;
		return this;
	}
	
	
	public Parking build() {
		return new Parking(id, placa, tipoVehiculo, cilindraje, fechaIngreso, fechaSalida, valorPago);
	}

}
