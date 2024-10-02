package dao;

import beans.Cadastro1;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {
    private Conexao conexao;
    private Connection conn;
    
    public CadastroDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Cadastro1 tb_cadastro){
        String sql = "INSERT INTO tb_cadastro (nm_cliente, ds_sex, dt_nascimento, ds_cpf, ds_telefone, ds_email, ds_senha) VALUES (?,?,?,?,?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, tb_cadastro.getNm_cliente());
            stmt.setString(2, tb_cadastro.getDs_sex());
            stmt.setInt(3,tb_cadastro.getDt_nascimento());
            stmt.setString(4,tb_cadastro.getDs_cpf());
            stmt.setString(5,tb_cadastro.getDs_telefone());
            stmt.setString(6,tb_cadastro.getDs_email());
            stmt.setString(7,tb_cadastro.getDs_senha());            
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir tb_cadastro: "+ e.getMessage());
        }
    }
    
    public void alterar(Cadastro1 tb_cadastro){
        String sql = "UPDATE tb_cadastro SET nm_cliente=?, ds_sex=?, dt_nascimento=?, ds_cpf=?, ds_telefone=?, ds_email=?, ds_senha=? WHERE id_cliente=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, tb_cadastro.getNm_cliente());
            stmt.setString(2, tb_cadastro.getDs_sex());
            stmt.setInt(3,tb_cadastro.getDt_nascimento());
            stmt.setString(4,tb_cadastro.getDs_cpf());
            stmt.setString(5,tb_cadastro.getDs_telefone());
            stmt.setString(6,tb_cadastro.getDs_email());
            stmt.setString(7,tb_cadastro.getDs_senha());  
            stmt.setInt(8,tb_cadastro.getId_cliente());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar tb_cadastro: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM tb_cadastro WHERE id_cliente = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir tb_cadastro: "+ e.getMessage());
        }
    }
    
    public Cadastro1 getCadastro(int id){
        String sql = "SELECT * FROM tb_cadastro WHERE id_cliente =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Cadastro1 tb_cadastro = new Cadastro1();
            rs.next();
            tb_cadastro.setId_cliente(rs.getInt("id"));
            tb_cadastro.setNm_cliente(rs.getString("cliente"));
            tb_cadastro.setDs_sex(rs.getString("sexo"));
            tb_cadastro.setDt_nascimento(rs.getInt("nascimento"));
            tb_cadastro.setDs_cpf(rs.getString("cpf"));
            tb_cadastro.setDs_telefone(rs.getString("telefone"));
            tb_cadastro.setDs_email(rs.getString("email"));
            tb_cadastro.setDs_senha(rs.getString("senha"));
            return tb_cadastro;
        
        }catch(Exception e){
            System.out.println("Erro ao atualizar tb_cadastro: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Cadastro1> getCadastro(){
        String sql = "SELECT * FROM tb_tb_cadastro";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Cadastro1> listaCadastro = new ArrayList<>();
          while(rs.next()){
              Cadastro1 p = new Cadastro1();
              p.setId_cliente(rs.getInt("id"));
              p.setNm_cliente(rs.getString("cliente"));
              p.setDs_sex(rs.getString("sexo"));
              p.setDt_nascimento(rs.getInt("nascimento"));
              p.setDs_cpf(rs.getString("cpf"));
              p.setDs_telefone(rs.getString("telefone"));
              p.setDs_email(rs.getString("email"));
              p.setDs_senha(rs.getString("senha"));
              listaCadastro.add(p);
          }
          return listaCadastro;
        }catch(Exception e){
            return null;
        }
    }
}

