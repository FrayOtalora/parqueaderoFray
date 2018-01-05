package co.ceiba.parqueaderoRest.dominio;

public class Moto extends Vehiculo {

	private int cilindraje;

	public Moto() {
		super();
	}

	public Moto(String placa, int cilindraje) {
		super(placa);
		this.cilindraje = cilindraje;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	@Override
	public String toString() {
		return "Moto{" + "placa:" + this.getPlaca() + " cilindraje:" + this.cilindraje + "} ";
	}

}
