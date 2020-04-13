package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Userposjava;

public class UserPosDAO {
	
	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar (Userposjava userposjava) {
		try {
			String sql = "insert into userposjava "
					   + "(nome, email) " //id retirado pois agora vai incrementar automaticamente;
					   + "values (?,?) ";//String do SQL
			//Retorna o objeto de instrução
			PreparedStatement insert = connection.prepareStatement(sql);
			//Parâmetros sendo adicionados:
			//insert.setLong(1, userposjava.getId());
			insert.setString(1, userposjava.getNome()); //Posições corrigidas!!!!
			insert.setString(2, userposjava.getEmail());
			//SQL sendo executado no Banco de Dados:
			insert.execute();
			//Salva no Banco de Dados:
			connection.commit();
		} catch (Exception e) {
			try {
				//Reverte a operação em caso de erro:
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		e.printStackTrace();	
		}
		
	}
	
	public List<Userposjava> listar() throws Exception {
		
		//Lista de retorno do método:
		List<Userposjava> list = new ArrayList<Userposjava>();
		
		//Istrução SQL:
		String sql = "select * from userposjava ";
		
		//Objeto de instrução:
		PreparedStatement statement = connection.prepareStatement(sql);
		
		//Executa a consulta ao Banco de Dados:
		ResultSet resultado = statement.executeQuery();
		
		//Iteramos percorrendo o objeto ResultSet que tem os dados:
		while (resultado.next()) {
			//Criamos um objeto para cada linha retornada:
			Userposjava userposjava = new Userposjava();
			
			//Setamos os valores para o objeto:
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
			
			//para cada objeto adicionamos ele á lista de retorno:
			list.add(userposjava);
		}
		return list;
	}
	
	public Userposjava buscar(Long id) throws Exception {
		
		Userposjava retorno = new Userposjava();
		
		//Sql recebendo o parâmetro:
		String sql = "select * from userposjava where id = " + id;
		
		//Instrução compilada:
		PreparedStatement statement = connection.prepareStatement(sql);
		//Consulta sendo realizada:
		ResultSet resultado = statement.executeQuery();
		
		//Retorna apenas um ou nenhum:
		while (resultado.next()) {
			//Capturando os dados e jogando no objeto:
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
		}
		return retorno;
	}
	
	public void atualizar(Userposjava userposjava) {
		
		try {
			//SQL usando SET para informar o nome valor:
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			
			//Compilando o SQL
			PreparedStatement sta = connection.prepareStatement(sql);
			//Passando o parâmetro para update
			sta.setString(1, userposjava.getNome());
			
			//Executando a atualização
			sta.execute();
			//Commitando/Gravando no Banco de Dados
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();//Reverte caso de algum erro
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
