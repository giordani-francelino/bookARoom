/*
 * Copyright (C) 2023 Your Name &lt;francelino at ifnmg&gt;
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.mycompany.bookaroom;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class Campus {

    private int codigo;
    private String nome;
    private String endereco;
   
    //<editor-fold defaultstate="collapsed" desc="construtores">
    
    public Campus(){
        
    }
    public Campus(Campus c){
        this.setCodigo(c.getCodigo());
        this.setNome(c.getNome());
        this.setEndereco(c.getEndereco());
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getters and setters">

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
//</editor-fold>

    @Override
    public String toString() {
        return "Campus{" + "codigo=" + codigo + ", nome=" + nome + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
//        final Campus other = (Campus) obj;
//        return this.codigo == other.codigo;
        return hashCode() == obj.hashCode();

    }

}
