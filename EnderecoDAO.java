package dao;

import beans.Endereco;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    private Conexao conexao;
    private Connection conn;
    
    public EnderecoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Endereco endereco){
        String sql = "INSERT INTO endereco (ds_cep, ds_numero_complemento, ds_rua, ds_bairro, ds_cidade, ds_estado) VALUES (?,?,?,?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, endereco.getDs_cep());
            stmt.setString(2, endereco.getDs_numero_complemento());
            stmt.setString(3,endereco.getDs_rua());
            stmt.setString(4,endereco.getDs_bairro());
            stmt.setString(5,endereco.getDs_cidade());
            stmt.setString(6,endereco.getDs_estado());           
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir endereco: "+ e.getMessage());
        }
    }
    
    public void alterar(Endereco endereco){
        String sql = "UPDATE endereco SET ds_cep=?, ds_numero_complemento=?, ds_rua=?, ds_bairro=?, ds_cidade=?, ds_estado=? WHERE id_endereco=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, endereco.getDs_cep());
            stmt.setString(2, endereco.getDs_numero_complemento());
            stmt.setString(3,endereco.getDs_rua());
            stmt.setString(4,endereco.getDs_bairro());
            stmt.setString(5,endereco.getDs_cidade());
            stmt.setString(6,endereco.getDs_estado());           
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar endereco: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM endereco WHERE id_endereco = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir endereco: "+ e.getMessage());
        }
    }
    
    public Endereco getEndereco(int id){
        String sql = "SELECT * FROM endereco WHERE id_endereco =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Endereco endereco = new Endereco();
            rs.next();
            endereco.setId_endereco(rs.getInt("id"));
            endereco.setDs_cep(rs.getString("cep"));
            endereco.setDs_numero_complemento(rs.getString("número complento"));
            endereco.setDs_rua(rs.getString("rua"));
            endereco.setDs_bairro(rs.getString("bairro"));
            endereco.setDs_cidade(rs.getString("cidade"));
            endereco.setDs_estado(rs.getString("estado"));
            return endereco;
        
        }catch(Exception e){
            System.out.println("Erro ao atualizar endereco: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Endereco> getEndereco(){
        String sql = "SELECT * FROM tb_endereco";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Endereco> listaEndereco = new ArrayList<>();
          while(rs.next()){
              Endereco p = new Endereco();
            p.setId_endereco(rs.getInt("id"));
            p.setDs_cep(rs.getString("cep"));
            p.setDs_numero_complemento(rs.getString("número complento"));
            p.setDs_rua(rs.getString("rua"));
            p.setDs_bairro(rs.getString("bairro"));
            p.setDs_cidade(rs.getString("cidade"));
            p.setDs_estado(rs.getString("estado"));
            listaEndereco.add(p);
          }
          return listaEndereco;
        }catch(Exception e){
            return null;
        }
    }
}

