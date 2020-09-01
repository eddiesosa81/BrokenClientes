package broken.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import broken.entidades.Clientes;
import broken.servicios.ServicioClientes;


@ViewScoped
@ManagedBean(name = "ControladorTopBar")
public class ControladorTopBar {
	@EJB
	private ServicioClientes srvCliente;

	public String usuarioId;
	public String usuarioCed;
	private String imagenUsr;

	public ControladorTopBar() {
		System.out.println("Ingreso topbar");
		usuarioId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("NMUSUARIO").toString();
		usuarioCed = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO").toString();
		imagenUsr = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("AVATARUSR").toString();
		
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getImagenUsr() {
		return imagenUsr;
	}

	public void setImagenUsr(String imagenUsr) {
		this.imagenUsr = imagenUsr;
	}

	
}
