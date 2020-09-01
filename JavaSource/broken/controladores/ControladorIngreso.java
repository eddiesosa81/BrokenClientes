package broken.controladores;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import broken.database.QueryDB;
import broken.entidades.Clientes;
import broken.servicios.ServicioClientes;

@ViewScoped
@ManagedBean(name = "ControladorIngreso")
public class ControladorIngreso {
	@EJB
	private ServicioClientes srvCliente;
	public String usuario;
	public String passwd;
	public String nombreCliente;

	private QueryDB srvConsDb;

	public ControladorIngreso() {
		srvConsDb = new QueryDB();
	}

	public void validaUsr() {
		try {

			System.out.println("Usuario:" + usuario);
			Integer valida = srvConsDb.verificaExisteClientes(usuario);
			if (valida >= 1) {
				System.out.println("usuario correcto");
				Clientes clie = srvCliente.clienteCedula(usuario.trim());
				nombreCliente = srvConsDb.nombreCliente(clie.getCd_cliente());
				String imagenUsr;
				try {
					if (clie.getNmlogotipo().isEmpty() || clie.getNmlogotipo() != null) {
						imagenUsr = clie.getNmlogotipo();
					} else {
						imagenUsr = "http://35.237.152.6:8081/images/clienteConfia/avatar.jpg";
					}
				} catch (Exception e) {
					imagenUsr = "http://35.237.152.6:8081/images/clienteConfia/avatar.jpg";
				}
				System.out.println("Imganen:"+imagenUsr);
				
				FacesMessage mensajebien = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido " + nombreCliente, null);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, mensajebien);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO",
						clie.getIdentificacion_cliente());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("NMUSUARIO",
						nombreCliente);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("AVATARUSR",
						imagenUsr);
				FacesContext.getCurrentInstance().getExternalContext().redirect("./inicio.jsf");

			} else {
				System.out.println("No existe el usuario ingresado o los datos son erroneos");
				FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error: Ingrese nuevamente usuario y contraseña.", null);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, mensaje);
			}
		} catch (Exception e) {
			System.out.println("No existe el usuario ingresado o los datos son erroneos");
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error: Ingrese nuevamente usuario y contraseña.", null);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, mensaje);
		}

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

}
