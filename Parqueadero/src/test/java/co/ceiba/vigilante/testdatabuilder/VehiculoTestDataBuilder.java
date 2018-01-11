package co.ceiba.vigilante.testdatabuilder;

import co.ceiba.vigilante.dominio.Vehiculo;

public class VehiculoTestDataBuilder {

	private String placa;
	private int tipo;
	private int cilindraje;
	
	public VehiculoTestDataBuilder() {
		super();
		this.placa = "R12525";
		this.tipo = 0;
		this.cilindraje = 0;
	}
	
	
	public VehiculoTestDataBuilder withPlaca(String placa) {
		this.placa=placa;
		return this;
	}
	
	public VehiculoTestDataBuilder withTipo(int tipo) {
		this.tipo=tipo;
		return this;
	}
	
	public VehiculoTestDataBuilder withCilindraje(int cilindraje) {
		this.cilindraje=cilindraje;
		return this;
	}
	
	public Vehiculo build() {
		return new Vehiculo(this.placa, this.tipo, this.cilindraje);
	}
	
	
	
	
	
}
