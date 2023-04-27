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
package com.mycompany.bookaroom.negocio;

import com.mycompany.bookaroom.cadastro.Funcionario;
import com.mycompany.bookaroom.cadastro.SalaReuniao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class Reserva implements Comparable<Reserva> {

    private LocalDate dataReserva;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String assunto;
    private SalaReuniao salaReuniao;
    private Funcionario funcionario;
    private boolean aula;
    private List<ItemEquipamento>  itemEquipamentos;

    //<editor-fold defaultstate="collapsed" desc="construtores">
    public Reserva() {
        salaReuniao = new SalaReuniao();
        funcionario = new Funcionario();
        itemEquipamentos = new ArrayList();
    }

    public Reserva(Reserva r) {
        this();
        this.setDataReserva(r.getDataReserva());
        this.setHoraInicio(r.getHoraInicio());
        this.setHoraFim(r.getHoraFim());
        this.setAssunto(r.getAssunto());
        this.setSalaReuniao(new SalaReuniao(r.getSalaReuniao()));
        this.setFuncionario(new Funcionario(r.getFuncionario()));
        this.setAula(r.isAula());
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public SalaReuniao getSalaReuniao() {
        return salaReuniao;
    }

    public void setSalaReuniao(SalaReuniao salaReuniao) {
        this.salaReuniao = salaReuniao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isAula() {
        return aula;
    }

    public void setAula(boolean aula) {
        this.aula = aula;
    }

    public List<ItemEquipamento> getItemEquipamentos() {
        return itemEquipamentos;
    }

    public void setItemEquipamentos(List<ItemEquipamento> itemEquipamentos) {
        this.itemEquipamentos = itemEquipamentos;
    }

    
    
    //</editor-fold>
    @Override
    public String toString() {
        return "Reserva{" + "Data=" + dataReserva + ", Hora início=" + horaInicio
                + ", Hora fim=" + horaFim + ", Sala=" + this.getSalaReuniao().getCodigo()
                + ", Predio=" + this.getSalaReuniao().getPredio().getCodigo()
                + ", Campus=" + this.getSalaReuniao().getPredio().getCampus().getCodigo()
                + ", Assunto=" + assunto + ", " + funcionario + "}\n"
                + "{" + itemEquipamentos + "}";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.dataReserva);
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.horaFim);
        hash = 37 * hash + Objects.hashCode(this.salaReuniao);
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
//        final Reserva other = (Reserva) obj;
//        if (!Objects.equals(this.dataReserva, other.dataReserva)) {
//            return false;
//        }
//        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
//            return false;
//        }
//        if (!Objects.equals(this.horaFim, other.horaFim)) {
//            return false;
//        }
//        return Objects.equals(this.salaReuniao, other.salaReuniao);
        return hashCode() == obj.hashCode();
    }

    @Override
    public int compareTo(Reserva outraReserva) {
        if (this.dataReserva.compareTo(outraReserva.getDataReserva()) > 0) {
            return 1;
        }
        if (this.dataReserva.compareTo(outraReserva.getDataReserva()) < 0) {
            return -1;
        }
        if (this.horaInicio.compareTo(outraReserva.getHoraInicio()) > 0) {
            return 1;
        }
        if (this.horaInicio.compareTo(outraReserva.getHoraInicio()) < 0) {
            return -1;
        }

        return 0;

    }

}
