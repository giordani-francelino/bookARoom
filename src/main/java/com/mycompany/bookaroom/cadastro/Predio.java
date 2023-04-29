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
package com.mycompany.bookaroom.cadastro;

import com.mycompany.bookaroom.cadastro.Campus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class Predio implements Serializable {

    private int codigo;
    private Campus campus;
    private String nome;
    private List<SalaReuniao> salaReuniaos;

    //<editor-fold defaultstate="collapsed" desc="construtores">
    public Predio() {
        campus = new Campus();
        salaReuniaos = new ArrayList();
    }

    public Predio(Predio p) {
        this();
        this.setCodigo(p.getCodigo());
        this.setCampus(new Campus(p.getCampus()));
        this.setNome(p.getNome());
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getters and setters">
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<SalaReuniao> getSalaReuniaos() {
        return salaReuniaos;
    }

    public void setSalaReuniaos(List<SalaReuniao> salaReuniaos) {
        this.salaReuniaos = salaReuniaos;
    }

    
    
//</editor-fold>
    @Override
    public String toString() {
        return "Predio{" + "codigo=" + codigo + ", campus=" + campus + ", nome=" + nome + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.codigo;
        hash = 71 * hash + Objects.hashCode(this.campus);
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
//        final Predio other = (Predio) obj;
//        if (this.codigo != other.codigo) {
//            return false;
//        }
//        return Objects.equals(this.campus, other.campus);
        return hashCode() == obj.hashCode();
    }

}
