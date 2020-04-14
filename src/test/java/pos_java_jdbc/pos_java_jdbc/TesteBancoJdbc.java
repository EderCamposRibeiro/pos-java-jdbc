package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {
	
	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();

		userposjava.setNome("Autoincrementar teste");
		userposjava.setEmail("autoincrement@gmail.com");
		
		userPosDAO.salvar(userposjava);
		
	}
	
	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.listar();
			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
				System.out.println("------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initBuscar() {
		
		UserPosDAO dao = new UserPosDAO();
		try {
			Userposjava userposjava = dao.buscar(3L);
			System.out.println(userposjava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initAtualizar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			Userposjava objetoBanco = dao.buscar(5L);
			
			objetoBanco.setNome("Teste altera nome");
			
			dao.atualizar(objetoBanco);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void deletar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			
			dao.deletar(7L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testeInsertTelefone() {

		Telefone telefone = new Telefone();

		telefone.setNumero("(61) 9 9676-5095");
		telefone.setTipo("celular");
		telefone.setUsuario(3L);
		
		UserPosDAO dao = new UserPosDAO();
		
		dao.salvarTelefone(telefone);
		
	}
	
	@Test
	public void testeNomeFoneEmailList() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<BeanUserFone> list = dao.listarUserFone(2L);
			for (BeanUserFone beanUserFone : list) {
				System.out.println(beanUserFone);
				System.out.println("------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeDeleteUserFone() {
		try {
			UserPosDAO dao = new UserPosDAO();
			
			dao.deletarFonesPorUser(2L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
