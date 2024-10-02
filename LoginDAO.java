/*verificar o login no bd!!!!!*/



package dao;

import beans.Login;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
    private Conexao conexao;
    private Connection conn;
    
    public LoginDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Login login){
        String sql = "INSERT INTO login (ds_email, ds_senha) VALUES (?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, login.getDs_email());
            stmt.setString(2, login.getDs_senha());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir login: "+ e.getMessage());
        }
    }
    
    public void alterar(Login login){
        String sql = "UPDATE login SET ds_email=?, ds_senha=? WHERE id_login=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, login.getDs_email());
            stmt.setString(2, login.getDs_senha());
            stmt.setInt(8,login.getId_login());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar login: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM login WHERE id_login = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir login: "+ e.getMessage());
        }
    }
    
    public Login getLogin(int id){
        String sql = "SELECT * FROM login WHERE id_login =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Login login = new Login();
            rs.next();
            login.setId_login(rs.getInt("id"));
            login.setDs_email(rs.getString("email"));
            login.setDs_senha(rs.getString("senha"));

            return login;
        
        }catch(Exception e){
            System.out.println("Erro ao atualizar login: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Login> getLogin(){
        String sql = "SELECT * FROM tb_login";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Login> listaLogin = new ArrayList<>();
          while(rs.next()){
              Login p = new Login();
              p.setId_login(rs.getInt("id"));
              p.setDs_email(rs.getString("email"));
              p.setDs_senha(rs.getString("senha"));
              listaLogin.add(p);
          }
          return listaLogin;
        }catch(Exception e){
            return null;
        }
    }
}

