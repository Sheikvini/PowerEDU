package dao;

import beans.Pagamentos;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PagamentosDAO {
    private Conexao conexao;
    private Connection conn;
    
    public PagamentosDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Pagamentos pagamentos){
        String sql = "INSERT INTO pagamentos (ds_ncartao, dt_vencimentocartao, ds_cvc, ds_formadepagamento, qtd_parcelas) VALUES (?,?,?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, pagamentos.getDs_ncartao());
            stmt.setInt(2, pagamentos.getDt_vencimentocartao());
            stmt.setString(3,pagamentos.getDs_cvc());
            stmt.setInt(4,pagamentos.getDs_fromadepagamento());
            stmt.setInt(5,pagamentos.getQtd_parcelas());          
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir pagamentos: "+ e.getMessage());
        }
    }
    
    public void alterar(Pagamentos pagamentos){
        String sql = "UPDATE pagamentos SET ds_ncartao=?, dt_vencimentocartao=?, ds_cvc=?, ds_formadepagamento=?, qtd_parcelas=? WHERE id_pagamentos=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, pagamentos.getDs_ncartao());
            stmt.setInt(2, pagamentos.getDt_vencimentocartao());
            stmt.setString(3,pagamentos.getDs_cvc());
            stmt.setInt(4,pagamentos.getDs_fromadepagamento());
            stmt.setInt(5,pagamentos.getQtd_parcelas());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar pagamentos: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM pagamentos WHERE id_pagamentos = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir pagamentos: "+ e.getMessage());
        }
    }
    
    public Pagamentos getPagamentos(int id){
        String sql = "SELECT * FROM pagamentos WHERE id_pagamentos =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Pagamentos pagamentos = new Pagamentos();
            rs.next();
            pagamentos.setId_pagamentos(rs.getInt("id"));
            pagamentos.setDs_ncartao(rs.getString("número do cartão"));
            pagamentos.setDt_vencimentocartao(rs.getInt("vencimento do cartão"));
            pagamentos.setDs_cvc(rs.getString("cvc"));
            pagamentos.setDs_fromadepagamento(rs.getInt("forma de pagamento"));
            pagamentos.setQtd_parcelas(rs.getInt("parcelas"));
            
            return pagamentos;
        
        }catch(Exception e){
            System.out.println("Erro ao atualizar pagamentos: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Pagamentos> getPagamentos(){
        String sql = "SELECT * FROM tb_pagamentos";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Pagamentos> listaPagamentos = new ArrayList<>();
          while(rs.next()){
              Pagamentos p = new Pagamentos();
            p.setId_pagamentos(rs.getInt("id"));
            p.setDs_ncartao(rs.getString("número do cartão"));
            p.setDt_vencimentocartao(rs.getInt("vencimento do cartão"));
            p.setDs_cvc(rs.getString("cvc"));
            p.setDs_fromadepagamento(rs.getInt("forma de pagamento"));
            p.setQtd_parcelas(rs.getInt("parcelas"));
              listaPagamentos.add(p);
          }
          return listaPagamentos;
        }catch(Exception e){
            return null;
        }
    }
}

