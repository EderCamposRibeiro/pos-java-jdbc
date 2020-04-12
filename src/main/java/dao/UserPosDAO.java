package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
					   + "(id, nome, email) "
					   + "values (?,?,?) ";//String do SQL
			//Retorna o objeto de instrução
			PreparedStatement insert = connection.prepareStatement(sql);
			//Parâmetros sendo adicionados:
			insert.setLong(1, userposjava.getId());
			insert.setString(2, userposjava.getNome());
			insert.setString(3, userposjava.getEmail());
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

}
