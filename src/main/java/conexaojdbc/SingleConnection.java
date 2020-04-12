package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String password = "admin";
	private static String user = "postgres";
	//Connection do java.sql:
	private static Connection connection = null;
	
	//Sempre que o SingleConnection for chamado ele vai acionar o conectar.
	//Não sendo necessário fazer isso na mão (Fica automatizado)
	static {
		conectar();
	}
	
	//Construtor chamando o método conectar
	public SingleConnection() {
		conectar();
	}
	
	//método privado "conectar" acessível apenas nessa classe.
	//conteúdo envolvido em um try/catch:
	private static void conectar() {
		//Dentro do try será escrita a nossa parte de conexão:
		try {
			//Fazer uma verificação se a conexão for nula ele conecta!
			//Obs: Conexão é feita apenas uma vez. O que é aberto e fechado é a seção!
			if (connection == null) {
				//Carregar o driver que vamos usar:
				//Como estamos usando o postgreSql:
				Class.forName("org.postgresql.Driver");
				//Atribui o DriveManager.getConnection para o objeto connection:
				connection = DriverManager.getConnection(url, user, password);
				//Para não slavar no banco automaticamente:
				connection.setAutoCommit(false);
				
				System.out.println("Conectou com sucesso!");
			}
		} catch (Exception e) {
			//Printar o caminho no console caso haja alguma exceção
			e.printStackTrace();
		}
	}
	
	//Método público que retorna o objeto "connection"
	public static Connection getConnection() {
		return connection;
	}

}
