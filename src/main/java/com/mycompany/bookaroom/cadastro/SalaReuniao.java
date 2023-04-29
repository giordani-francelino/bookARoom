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

import com.mycompany.bookaroom.cadastro.Predio;
import com.mycompany.bookaroom.negocio.Reserva;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class SalaReuniao implements Comparable<SalaReuniao>, Serializable {

    private int codigo;
    private Predio predio;
    private int numLugares;
    private List<Reserva> reservas;

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public SalaReuniao() {
        predio = new Predio();
        reservas = new ArrayList();
    }

    public SalaReuniao(SalaReuniao sr) {
        this();
        this.setCodigo(sr.getCodigo());
        this.setPredio(new Predio(sr.getPredio()));
        this.setNumLugares(sr.getNumLugares());
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public int getNumLugares() {
        return numLugares;
    }

    public void setNumLugares(int numLugares) {
        this.numLugares = numLugares;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

//</editor-fold>
    @Override
    public String toString() {
        return "SalaReuniao{" + "codigo=" + codigo + ", predio=" + predio + ", numLugares=" + numLugares + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.codigo;
        hash = 53 * hash + Objects.hashCode(this.predio);
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
//        final SalaReuniao other = (SalaReuniao) obj;
//        if (this.codigo != other.codigo) {
//            return false;
//        }
//        return Objects.equals(this.predio, other.predio);
        return hashCode() == obj.hashCode();

    }

    @Override
    public int compareTo(SalaReuniao outraSala) {
        if (this.getPredio().getCampus().getCodigo() > outraSala.getPredio().getCampus().getCodigo()) {
            return 1;
        }
        if (this.getPredio().getCampus().getCodigo() < outraSala.getPredio().getCampus().getCodigo()) {
            return -1;
        }
        if (this.getPredio().getCodigo() > outraSala.getPredio().getCodigo()) {
            return 1;
        }
        if (this.getPredio().getCodigo() < outraSala.getPredio().getCodigo()) {
            return -1;
        }
        if (this.getCodigo() > outraSala.getCodigo()) {
            return 1;
        }
        if (this.getCodigo() < outraSala.getCodigo()) {
            return -1;
        }
        return 0;
    }

}
