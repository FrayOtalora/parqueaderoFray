package co.ceiba.vigilante.dominio;

import java.io.Serializable;
import java.util.Date;

public class Parking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 615979173739091224L;
	private int id;
	private Vehiculo vehiculo;
	private Date fechaIngreso;
	private Date fechaRetiro;
	private float valorPago;

	public Parking() {

	}

	public Parking(int id, Vehiculo vehiculo, Date fechaIngreso, Date fechaRetiro, float valorPago) {
		super();
		this.id = id;
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaRetiro = fechaRetiro;
		this.valorPago = valorPago;
	}

	public Parking(Vehiculo vehiculo, Date fechaIngreso) {
		super();
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public float getValorPago() {
		return valorPago;
	}

	public void setValorPago(float valorPago) {
		this.valorPago = valorPago;
	}

	@Override
	public String toString() {

		return "Parking [id=" + id + ", vehiculo=" + vehiculo + ", fechaIngreso=" + fechaIngreso + ", fechaRetiro="
				+ fechaRetiro + ", valorPago=" + valorPago + "]";
	}

}
