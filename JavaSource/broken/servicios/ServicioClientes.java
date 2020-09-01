package broken.servicios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import broken.entidades.Clientes;

@Stateless
public class ServicioClientes {
	@PersistenceContext
	private EntityManager em;

	public Clientes clienteCedula(String cedulaClie) {
		String sql = "select * from cliente_tbl where identificacion_cliente = '" + cedulaClie.trim() + "'";
		System.out.println("SQL->"+sql);
		try {
			Query query = em.createNativeQuery(sql, Clientes.class);

			Clientes resultado = new Clientes();
			resultado = (Clientes) query.getSingleResult();

			return resultado;
		} catch (Exception ex) {
			return null;
		}
	}

	public void actualizaClientes(Clientes obj) {
		em.merge(obj);
	}

	public void eliminaClientes(Clientes obj) {
		em.remove(em.merge(obj));
	}
}
