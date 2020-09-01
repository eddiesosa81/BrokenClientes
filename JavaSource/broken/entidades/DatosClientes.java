package broken.entidades;

public class DatosClientes {
	
	private String cdCliente;
	private String direccion;
	private String telefono;
	private String contacto;
	private String correo;
	public DatosClientes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DatosClientes(String cdCliente, String direccion, String telefono, String contacto, String correo) {
		super();
		this.cdCliente = cdCliente;
		this.direccion = direccion;
		this.telefono = telefono;
		this.contacto = contacto;
		this.correo = correo;
	}
	public String getCdCliente() {
		return cdCliente;
	}
	public void setCdCliente(String cdCliente) {
		this.cdCliente = cdCliente;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

}
