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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
@Entity
@Table
public class Campus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @NotNull
    @Column(length = 100)
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "campus_codigo")
    private List<Predio> predios;

    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "campus_codigo")
    private List<Equipamento> equipamentos;

    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "campus_codigo")
    private List<Funcionario> funcionarios;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endreco_codigo")
    private Endereco endereco;

    //<editor-fold defaultstate="collapsed" desc="construtores">
    public Campus() {
        predios = new ArrayList<>();
        equipamentos = new ArrayList<>();
        funcionarios = new ArrayList<>();
        if (endereco == null) {
            endereco = new Endereco(this);
        }
    }

    public Campus(Endereco endereco) {
        predios = new ArrayList<>();
        equipamentos = new ArrayList<>();
        funcionarios = new ArrayList<>();
        this.endereco = endereco;
    }

    public Campus(Campus c) {
        this();
        this.setCodigo(c.getCodigo());
        this.setNome(c.getNome());
        this.setEndereco(new Endereco());
        this.getEndereco().setCodigo(c.getEndereco().getCodigo());
        this.getEndereco().setLogradouro(c.getEndereco().getLogradouro());
        this.getEndereco().setNumero(c.getEndereco().getNumero());
        this.getEndereco().setBairro(c.getEndereco().getBairro());
        this.getEndereco().setCidade(c.getEndereco().getCidade());
        this.getEndereco().setEstado(c.getEndereco().getEstado());

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Predio> getPredios() {
        return predios;
    }

    public void setPredios(List<Predio> predios) {
        this.predios = predios;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
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
