package dao;

import beans.Paineis_Solares;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Paineis_SolaresDAO {
    private Conexao conexao;
    private Connection conn;
    
    public Paineis_SolaresDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Paineis_Solares paineis_solares){
        String sql = "INSERT INTO paineis_solares (nm_modelo, ds_potencia, ds_fabricante, ds_eficiencia) VALUES (?,?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, paineis_solares.getNm_modelo());
            stmt.setInt(2, paineis_solares.getDs_potencia());
            stmt.setString(3,paineis_solares.getDs_fabricante());
            stmt.setInt(4,paineis_solares.getDs_eficiencia());           
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir paineis solares: "+ e.getMessage());
        }
    }
    
    public void alterar(Paineis_Solares paineis_solares){
        String sql = "UPDATE paineis_solares SET nm_modelo=?, ds_potencia=?, ds_fabricante=?, ds_eficiencia=? WHERE id_paineis_solares=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, paineis_solares.getNm_modelo());
            stmt.setInt(2, paineis_solares.getDs_potencia());
            stmt.setString(3,paineis_solares.getDs_fabricante());
            stmt.setInt(4,paineis_solares.getDs_eficiencia());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar paineis solares: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM paineis_solares WHERE id_paineis_solares = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir paineis solares: "+ e.getMessage());
        }
    }
    
    public Paineis_Solares getPaineis_Solares(int id){
        String sql = "SELECT * FROM paineis_solares WHERE id_paineis_solares =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Paineis_Solares paineis_solares = new Paineis_Solares();
            rs.next();
            paineis_solares.setId_paineis_solares(rs.getInt("id"));
            paineis_solares.setNm_modelo(rs.getString("modelo"));
            paineis_solares.setDs_potencia(rs.getInt("potência"));
            paineis_solares.setDs_fabricante(rs.getString("fabricante"));
            paineis_solares.setDs_eficiencia(rs.getInt("eficiencia"));
            return paineis_solares;
        
        }catch(Exception e){
            System.out.println("Erro ao atualizar paineis solares: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Paineis_Solares> getPaineis_Solares(){
        String sql = "SELECT * FROM tb_paineis_solares";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Paineis_Solares> listaPaineis_Solares = new ArrayList<>();
          while(rs.next()){
              Paineis_Solares p = new Paineis_Solares();
            p.setId_paineis_solares(rs.getInt("id"));
            p.setNm_modelo(rs.getString("modelo"));
            p.setDs_potencia(rs.getInt("potência"));
            p.setDs_fabricante(rs.getString("fabricante"));
            p.setDs_eficiencia(rs.getInt("eficiencia"));
              listaPaineis_Solares.add(p);
          }
          return listaPaineis_Solares;
        }catch(Exception e){
            return null;
        }
    }
}

