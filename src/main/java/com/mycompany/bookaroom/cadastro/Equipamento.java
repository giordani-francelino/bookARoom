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

import java.util.Objects;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class Equipamento {

    private int codigo;
    private Campus campus;
    private int tipo;
    private String[] tipos;
    private String nome;
    private int patrimonio;

    //<editor-fold defaultstate="collapsed" desc="construtores">
    public Equipamento() {
        campus = new Campus();
        tipos = new String[7];
        tipos[0] = "apontador a laser e passador de slides";
        tipos[1] = "áudio e vídeo";
        tipos[2] = "caixa de som";
        tipos[3] = "controle de ar condicionado";
        tipos[4] = "impressora 3D";
        tipos[5] = "mesa";
        tipos[6] = "notebook";
    }

    public Equipamento(Equipamento e) throws Exception {
        this();
        this.setCodigo(e.getCodigo());
        this.setCampus(new Campus(e.getCampus()));
        this.setTipo(e.getTipo());
        this.setPatrimonio(e.getPatrimonio());
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) throws Exception {
        this.tipo = tipo;
        for (int t = 0; t < tipos.length; t++) {
            if (t == tipo) {
                this.nome = tipos[t];
            }
        }
        if (tipo < 0 || tipo >= tipos.length) {
            throw new Exception("Tipo inválido.");
        }
    }

    public String[] getTipos() {
        return tipos;
    }

    public String getNome() {
        return nome;
    }

    public int getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(int patrimonio) {
        this.patrimonio = patrimonio;
    }

//</editor-fold>
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.codigo;
        hash = 47 * hash + Objects.hashCode(this.campus);
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
//        final Equipamento other = (Equipamento) obj;
//        if (this.codigo != other.codigo) {
//            return false;
//        }
//        return Objects.equals(this.campus, other.campus);
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Equipamento{" + "codigo=" + codigo + ", tipo = " + tipos[tipo] + "}";
    }

}
