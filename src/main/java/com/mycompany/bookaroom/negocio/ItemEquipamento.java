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

import com.mycompany.bookaroom.negocio.Reserva;
import com.mycompany.bookaroom.cadastro.Equipamento;
import java.util.Objects;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class ItemEquipamento {

    private Reserva reserva = new Reserva();
    private Equipamento equipamento = new Equipamento();
    
//<editor-fold defaultstate="collapsed" desc="construtores">
    
    public ItemEquipamento(){
        
    }
    
    public ItemEquipamento(ItemEquipamento re){
        this.setReserva(new Reserva(re.getReserva()));
        this.setEquipamento(new Equipamento(re.getEquipamento()));
    }
//</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

//</editor-fold>
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.getReserva().getDataReserva());
        hash = 71 * hash + Objects.hashCode(this.getReserva().getHoraInicio());
        hash = 71 * hash + Objects.hashCode(this.getReserva().getHoraFim());
        hash = 71 * hash + Objects.hashCode(this.getReserva().getSalaReuniao().getCodigo());
        hash = 71 * hash + Objects.hashCode(this.getReserva().getSalaReuniao().getPredio().getCodigo());
        hash = 71 * hash + Objects.hashCode(this.getReserva().getSalaReuniao().getPredio().getCampus().getCodigo());
        hash = 71 * hash + Objects.hashCode(this.getEquipamento().getCodigo());
        hash = 71 * hash + Objects.hashCode(this.getEquipamento().getCampus().getCodigo());
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
        return hashCode() == obj.hashCode();

    }

}
