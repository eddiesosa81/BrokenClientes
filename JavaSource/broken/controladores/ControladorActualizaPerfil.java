package broken.controladores;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import broken.database.ProceduresDB;
import broken.database.QueryDB;
import broken.entidades.DatosClientes;

@ViewScoped
@ManagedBean(name = "ControladorActualizaPerfil")
public class ControladorActualizaPerfil {
	private QueryDB srvConsultaDb;
	private ProceduresDB srvProcedimientoDb;
	
	public String usuarioNm;
	public String imagenUsr;
	public String usuarioCed;
	public String direcClie;
	public String telefClie;
	public String contClie;
	public String correClie;
	private DatosClientes datosAdcionalesCliente ;
	

  

	public ControladorActualizaPerfil() {
		System.out.println("Ingreso topbar");
		usuarioNm = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("NMUSUARIO").toString();
		usuarioCed = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO").toString();
		imagenUsr = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("AVATARUSR").toString();
		srvConsultaDb = new QueryDB();
		srvProcedimientoDb = new ProceduresDB();
	}
	@PostConstruct
	private void inicio() {
		datosAdcionalesCliente = srvConsultaDb.datosAdcionalesCliente(usuarioCed);
		if(datosAdcionalesCliente != null) {
			direcClie = datosAdcionalesCliente.getDireccion();
			telefClie = datosAdcionalesCliente.getTelefono();
			contClie = datosAdcionalesCliente.getContacto();
			correClie = datosAdcionalesCliente.getCorreo();
		}

	}
	public void guardar() {
		System.out.println("INGRESO:"+direcClie);
		try {
			if(direcClie.isEmpty()|| direcClie == null ) {
				FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Ingrese su Dirección.", null);
				return;
				
			}
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ingrese su Dirección.", null);
			return;
		}
		try {
			if(telefClie.isEmpty()|| telefClie == null ) {
				FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Ingrese su Teléfono.", null);
				return;
				
			}
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ingrese su Teléfono.", null);
			return;
		}
		try {
			if(contClie.isEmpty()|| contClie == null ) {
				FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Ingrese un Contacto.", null);
				return;
				
			}
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ingrese un Contacto.", null);
			return;
		}
		try {
			if(correClie.isEmpty()|| correClie == null ) {
				FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Ingrese un correo.", null);
				return;
			}
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ingrese un correo.", null);
			return;
		}
		
		 datosAdcionalesCliente.setDireccion(direcClie);
		 datosAdcionalesCliente.setTelefono(telefClie);
		 datosAdcionalesCliente.setContacto(contClie);
		 datosAdcionalesCliente.setCorreo(correClie);
		
		Integer res = srvProcedimientoDb.actDatosCliente(datosAdcionalesCliente);
		if(res==1) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Comuníquese con el Administrador del Sistema.", null);
			
		}else {
			FacesContext fContextObj = FacesContext.getCurrentInstance();
			fContextObj.addMessage(null, new FacesMessage("Advertencia",
					"Actualización Exitosa."));
		}
	}
	


	public String getUsuarioNm() {
		return usuarioNm;
	}

	public void setUsuarioNm(String usuarioNm) {
		this.usuarioNm = usuarioNm;
	}

	public String getImagenUsr() {
		return imagenUsr;
	}

	public void setImagenUsr(String imagenUsr) {
		this.imagenUsr = imagenUsr;
	}

	public String getUsuarioCed() {
		return usuarioCed;
	}

	public void setUsuarioCed(String usuarioCed) {
		this.usuarioCed = usuarioCed;
	}

	public String getDirecClie() {
		return direcClie;
	}

	public void setDirecClie(String direcClie) {
		this.direcClie = direcClie;
	}

	public String getTelefClie() {
		return telefClie;
	}

	public void setTelefClie(String telefClie) {
		this.telefClie = telefClie;
	}

	public String getContClie() {
		return contClie;
	}

	public void setContClie(String contClie) {
		this.contClie = contClie;
	}

	public String getCorreClie() {
		return correClie;
	}

	public void setCorreClie(String correClie) {
		this.correClie = correClie;
	}

	
	

}
