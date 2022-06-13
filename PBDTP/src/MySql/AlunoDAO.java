/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQL;

import Classes.Aluno;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import MySql.DataSource;
import javax.swing.JOptionPane;


/**
 *
 * @author mathe
 */
public class AlunoDAO {
    private DataSource dataSource;
    
    public AlunoDAO (DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    public ArrayList<Aluno> readAll(){
        try{
            String SQL = "SELECT * FROM aluno";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Aluno> lista = new ArrayList<Aluno>();
            
            while(rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setNome(rs.getString("nome"));
                aluno.setDataNascimento(rs.getString("dataNascimento"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                aluno.setEndereco(rs.getString("endereco"));
                lista.add(aluno);
            }
            ps.close();
            return lista;
        }
            
        
        catch(SQLException ex){
            System.err.println("Erro ao Recuperar "+ex.getMessage());
        }
        catch(Exception ex){
            System.err.println("Erro Geral... "+ex.getMessage());    
        }
        return null;
}
    
    public void create(Aluno c) throws SQLException{
       String SQL = "insert into aluno (cpf, nome, dataNascimento, telefone, email, endereco) values (?, ?, ?, ?, ?, ?)";
       PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
       try{
           ps.setString(1, c.getCpf());
           ps.setString(2, c.getNome());
           ps.setString(3, c.getDataNascimento());
           ps.setString(4, c.getTelefone());
           ps.setString(5, c.getEmail());
           ps.setString(6, c.getEndereco());
           
           ps.executeUpdate();
       }
       catch(SQLException e){
           JOptionPane.showConfirmDialog(null, "Erro ao salvar os dados "+e.toString());
       }
       finally{
           ps.close();
       }
    }
    
    public void update(Aluno c) throws SQLException{
        String SQL = "update aluno set cpf= ?, nome= ?, dataNascimento= ?, telefone= ?, email= ?, endereco= ? where id= ?";
        PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
        try{
            ps.setString(1, c.getCpf());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getDataNascimento());
            ps.setString(4, c.getTelefone());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getEndereco());
            ps.setInt(7, c.getId());
            
            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null, "Erro ao alterar os dados "+e.toString());
        }
        finally{
           ps.close();
        }
    }
    
    public void delete(Aluno c) throws SQLException{
        String SQL = "delete from aluno where id= ?";
        PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
        
        try{
            ps.setInt(1, c.getId());
            
            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null, "Erro ao Excluir dados "+e.toString());
        }
        finally{
           ps.close();
        }
    }
}
