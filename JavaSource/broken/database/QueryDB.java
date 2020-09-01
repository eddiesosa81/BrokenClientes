package broken.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import broken.entidades.DatosClientes;

public class QueryDB {
	
	public int verificaExisteClientes(String IdentClie) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select count(*) as cliente from Cliente_tbl where trim(identificacion_cliente) = '"+IdentClie+"'";
			System.out.println("SQL ->"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cliente");
				System.out.println("Resultado:"+resultado);
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = "0";
		}
		return Integer.parseInt(resultado);
	}
	
	
	
	public String nombreCliente(Integer cedClie) {
		Connection conn;
		String sql;
		String resultado = "0";
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select nombrecliente("+cedClie+") as cliente from dual";
			System.out.println("SQL ->"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			if (res.next()) {
				resultado = res.getString("cliente");
				System.out.println("NOMBRE CLIENTE:"+resultado);
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = "0";
		}
		return resultado;
	}
	
	public DatosClientes datosAdcionalesCliente(String cedClie) {
		Connection conn;
		String sql;
		DatosClientes  datosCliente = new DatosClientes();
		try {
			conn = ConectarBase.getOracleConnection();
			sql = "select correocliente(cd_cliente) as correo,direccionCliente(cd_cliente) as direccion," + 
					"telefonocliente(cd_cliente) as telefono,contactoCliente(cd_cliente) as contacto, cd_cliente " + 
					"from cliente_tbl where identificacion_cliente = '"+cedClie+"'";
			System.out.println("SQL ->"+sql);
			PreparedStatement a = conn.prepareStatement(sql);
			ResultSet res = a.executeQuery();
			while (res.next()) {
				datosCliente.setCorreo(res.getString("correo"));
				datosCliente.setDireccion(res.getString("direccion"));
				datosCliente.setTelefono(res.getString("telefono"));
				datosCliente.setContacto(res.getString("contacto"));
				datosCliente.setCdCliente(res.getString("cd_cliente"));
			}
			res.close();
			a.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datosCliente = null;
		}
		return datosCliente;
	}

	
}
