package broken.database;

import java.sql.CallableStatement;
import java.sql.Connection;

import broken.entidades.DatosClientes;


public class ProceduresDB {
	
	public Integer actDatosCliente(DatosClientes datoCliente) {
		Connection conn;
		// ejecuta el SP
		try {
			System.out.println("ORACLE PROCEDURE-> actuliza_datos_cliente_sp ( codClie IN VARCHAR2, lsdireccion IN VARCHAR2, "
					+ "lstelefono IN VARCHAR2, contacto In VARCHAR2, lscorreo IN VARCHAR2) IS");
			conn = ConectarBase.getOracleConnection();
			String proc3StoredProcedure = "{ call actuliza_datos_cliente_sp(?,?,?,?,?) }";
			CallableStatement cs = conn.prepareCall(proc3StoredProcedure);
			cs.setString(1, datoCliente.getCdCliente());
			cs.setString(2, datoCliente.getDireccion());
			cs.setString(3, datoCliente.getTelefono());
			cs.setString(4, datoCliente.getContacto());
			cs.setString(5, datoCliente.getCorreo());
			cs.execute();
			cs.close();
			conn.close();
			return 0;
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			return 1;
		}
	}

}
