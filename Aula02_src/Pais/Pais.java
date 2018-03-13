package Pais;

	import java.sql.Connection; 
	import java.sql.DriverManager; 
	import java.sql.PreparedStatement; 
	import java.sql.ResultSet; 
	import java.sql.SQLException; 


public class Pais {
	
		private int id;
		private String nome;
		private long populacao;
		private double area;
		
		//conexão com mySQl
		
		 static {   
	     try {    
	     Class.forName("com.mysql.jdbc.Driver");   
	     } catch (ClassNotFoundException e) {    
	     throw new RuntimeException(e);   
	     
		 }  
       }
		
		// metodos get e set
		 
		 public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public long getPopulacao() {
			return populacao;
		}
		public void setPopulacao(long populacao) {
			this.populacao = populacao;
		}
		public double getArea() {
			return area;
		}
		public void setArea(double area) {
			this.area = area;
		}
			 public Pais (int id, String nome, long populacao, double area) {  
				 this.id = id;   
				 this.nome = nome;   
				 this.populacao = populacao;   
				 this.area = area; 
		}
			 
             //obtendo conexão com o banco

		public Connection obtemConexao() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost");
			}
		
			public void criar() {
			String sqlInsert = "INSERT INTO Pais (nome, populacao, area) VALUES (?, ?, ?)";
			
			// usando o try with resources do Java 7, que fecha o que abriu
			
			try (Connection connection = obtemConexao();
					
			PreparedStatement stm = connection.prepareStatement(sqlInsert);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			
			try(PreparedStatement stm2 = connection.prepareStatement(sqlQuery);
			ResultSet rs = stm2.executeQuery();) {
			if(rs.next()){
			setId(rs.getInt(1));
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
	}
			
			public void atualizar() {
			String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
			
			// usando o try with resources do Java 7, que fecha o que abriu
			
			try (Connection conn = obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.setInt(4, getId());
			stm.execute();
			
			} catch (Exception e) {
			e.printStackTrace();
			}
			
			}
			public void excluir() {
			String sqlDelete = "DELETE FROM pais WHERE id = ?";
			
			// usando o try with resources do Java 7, que fecha o que abriu
			
			try (Connection conn = obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, getId());
			stm.execute();
			
			} catch (Exception e) {
			e.printStackTrace();
			}
			
			}
			public void carregar() {
			String sqlSelect = "SELECT nome, populacao, area FROM paises WHERE pais.id = ?";
			
			// usando o try with resources do Java 7, que fecha o que abriu
			
			try (Connection conn = obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, getId());
			
			try (ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
			setNome(rs.getString("nome"));
			setPopulacao(rs.getLong("populacao"));
			setArea(rs.getDouble("area"));
			
			} else {
			setId(-1);
			setNome(null);
			setPopulacao(0);
			setArea(0);
			
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
		    } catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		    
		}
	}
			public void paisNumHabi() {
				
				String sqlSelect = "SELECT * from pais where populacao = (Select Max(populacao) from pais)";
				
				// usando o try with resources do Java 7, que fecha o que abriu
				
				try (Connection connection = obtemConexao()){
					PreparedStatement stm = connection.prepareStatement(sqlSelect);
					
					ResultSet rs = stm.executeQuery();
					
					if(rs.next()) {
						
						setNome(rs.getString("nome"));
						setPopulacao(rs.getLong("populacao"));
						setId(rs.getInt("id"));
						setArea(rs.getDouble("area"));
						
					} else {
						setId(-1);
						setNome(null);
						setPopulacao(0);
						setArea(0);
						}
					
				} catch (SQLException e) {
					e.printStackTrace();
				
				} 
				
			 }
			public void paisMeArea() {
				
				String sqlSelect = "SELECT * from paises where area = (Select Min(area) from paises)";
				
				// usando o try with resources do Java 7, que fecha o que abriu
				
				try (Connection conn = obtemConexao()){
					PreparedStatement stm = conn.prepareStatement(sqlSelect);
					
					ResultSet rs = stm.executeQuery();
					
					if(rs.next()) {

						setNome(rs.getString("nome"));
						setPopulacao(rs.getLong("populacao"));
						setId(rs.getInt("id"));
						setArea(rs.getDouble("area"));
						
					} else {
						setId(-1);
						setNome(null);
						setPopulacao(0);
						setArea(0);
						}
					
				   } catch (SQLException e) {
					   e.printStackTrace();
						   
					   }
				   }
				 
				
				public String[] Vetor(){
					
					String sqlGet = "Select nome from paises order by nome";
					String vet[] = new String[3];
					int cont = 0;
					
					try (Connection connection = obtemConexao()){
						PreparedStatement stm = connection.prepareStatement(sqlGet);
							
						ResultSet rs = stm.executeQuery();
						
						while(rs.next() && cont < 3) {
							vet[cont] = rs.getString("nome");
							cont++;
						}	
					}
			catch (SQLException e) {
				e.printStackTrace();
			} 
		return vet;
	 }
			}




		   
