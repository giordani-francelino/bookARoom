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
package com.mycompany.bookaroom.bd;

import com.mycompany.bookaroom.negocio.ItemEquipamento;
import com.mycompany.bookaroom.negocio.Reserva;
import com.mycompany.bookaroom.cadastro.Equipamento;
import com.mycompany.bookaroom.cadastro.Funcionario;
import com.mycompany.bookaroom.cadastro.SalaReuniao;
import com.mycompany.bookaroom.cadastro.Predio;
import com.mycompany.bookaroom.cadastro.Campus;
import com.mycompany.bookaroom.cadastro.Endereco;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class Repositorio {

    public Repositorio() {
        if (primeiroObjeto) {
            return;
        }
        for (int codigoCampus = 1; codigoCampus < 4; codigoCampus++) {
            int item = 1;
            Campus campus = new Campus();
            Endereco endereco = new Endereco();
            campus.setCodigo(codigoCampus);
            campus.setNome("Campus " + codigoCampus);
            endereco.setCodigo(codigoCampus);
            campus.setEndereco(endereco);
            endereco.setCampus(campus);
//            campus.setEndereco("Endereco " + codigoCampus);
            try {
                Repositorio.gravaCampus(campus);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            for (int codigoPredio = 1; codigoPredio < 4; codigoPredio++) {
                Predio predio = new Predio();
                predio.setCodigo(codigoPredio);
                predio.setCampus(campus);
                predio.setNome("Prédio" + codigoPredio + " - Campus " + codigoCampus);
                try {
                    Repositorio.gravaPredio(predio);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());

                }
//              inclui salas para teste
                for (int codigoSalaReuniao = 1; codigoSalaReuniao < 4; codigoSalaReuniao++) {
                    SalaReuniao salaReuniao = new SalaReuniao();
                    salaReuniao.setCodigo(codigoSalaReuniao);
                    salaReuniao.setPredio(predio);
                    salaReuniao.setNumLugares(20 + codigoSalaReuniao + codigoPredio + codigoCampus);
                    try {
                        Repositorio.gravaSalaReuniao(salaReuniao);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    
                    Reserva reserva = new Reserva();
                    reserva.setDataReserva(LocalDate.now());
                    reserva.setHoraInicio(LocalTime.parse("11:00"));
                    reserva.setHoraFim(LocalTime.parse("12:00"));
                    reserva.setSalaReuniao(salaReuniao);
                    reserva.setFuncionario(new Funcionario());
                    reserva.getFuncionario().setCodigo(codigoPredio);
                    reserva.getFuncionario().setCampus(campus);
                    
                    reserva.setAssunto("Teste " + codigoSalaReuniao);
                    try {
                        Repositorio.gravaReserva(reserva);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    
                    ItemEquipamento itemEquipamento = new ItemEquipamento();
                    itemEquipamento.setReserva(reserva);
                    itemEquipamento.getEquipamento().setCodigo(item);
                    itemEquipamento.getEquipamento().setCampus(campus);
                    item++;
                    try {
                        Repositorio.gravaItemEquipamento(itemEquipamento);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    

                }
            }
            for (int codigoFuncionario = 1; codigoFuncionario < 11; codigoFuncionario++) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(codigoFuncionario);
                funcionario.setCampus(campus);
                funcionario.setNome("Funcioánrio " + codigoFuncionario + " - Campus " + codigoCampus);
                try {
                    Repositorio.gravaFuncionario(funcionario);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
            for (int codigoEquipamento = 1; codigoEquipamento < 200; codigoEquipamento++) {
                Equipamento equipamento = new Equipamento();
                equipamento.setCodigo(codigoEquipamento);
                equipamento.getCampus().setCodigo(codigoCampus);
//                equipamento.setNome("Equipamento " + codigoEquipamento + " - Campus " + codigoCampus);
                try {
                    Repositorio.gravaEquipamento(equipamento);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }

        }
        primeiroObjeto = true;
    }
    private static boolean primeiroObjeto = false;
    private static ArrayList<Campus> campuss = new ArrayList<Campus>();
    private static ArrayList<Predio> predios = new ArrayList<Predio>();
    private static ArrayList<SalaReuniao> salaReuniaos = new ArrayList<SalaReuniao>();
    private static ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
    private static ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
    private static ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
    private static ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private static ArrayList<ItemEquipamento> itemEquipamentos=new ArrayList<ItemEquipamento>();

//<editor-fold defaultstate="collapsed" desc="crud campus">
    public static boolean consultaCampus(Campus campus) {
        for (Campus c : campuss) {
            if (campus.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean gravaCampus(Campus campus) throws Exception {
        if (consultaCampus(campus)) {
            throw new Exception("Campus já cadastrado.");
        }
        campuss.add(campus);
        return true;
    }

    public static boolean excluiCampus(Campus campus) throws Exception {
        if (!consultaCampus(campus)) {
            throw new Exception("Campus não cadastrado.");
        }
        for (Iterator<Campus> iterator = campuss.iterator(); iterator.hasNext();) {
            Campus c = iterator.next();
            if (campus.equals(c)) {
                iterator.remove();
                return true;
            }
        }
        return true;
    }

    public static Campus recuperaCampus(int codigoCampus) throws Exception {
        for (Iterator<Campus> iterator = campuss.iterator(); iterator.hasNext();) {
            Campus c = iterator.next();
            if (c.getCodigo() == codigoCampus) {
                return c;
            }
        }
        throw new Exception("Campus não cadastrado.");
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="crud predio">
    public static boolean consultaPredio(Predio predio) {
        for (Iterator<Predio> iterator = predios.iterator(); iterator.hasNext();) {
            Predio p = iterator.next();
            if (predio.equals(p)) {
                return true;
            }
        }
        return false;
    }

    public static boolean gravaPredio(Predio predio) throws Exception {
        if (consultaPredio(predio)) {
            throw new Exception("Predio já cadastrado.");
        }
        predios.add(predio);
        return true;
    }

    public static boolean excluiPredio(Predio predio) throws Exception {
        if (!consultaPredio(predio)) {
            throw new Exception("Predio não cadastrado.");
        }
        for (Iterator<Predio> iterator = predios.iterator(); iterator.hasNext();) {
            Predio c = iterator.next();
            if (predio.equals(c)) {
                iterator.remove();
                return true;
            }
        }
        return true;
    }

    public static Predio recuperaPredio(int codigoPredio, int codigoCampus) throws Exception {
        for (Iterator<Predio> iterator = predios.iterator(); iterator.hasNext();) {
            Predio c = iterator.next();
            if (c.getCodigo() == codigoPredio && c.getCampus().getCodigo() == codigoCampus) {
                return c;
            }
        }
        throw new Exception("Predio não cadastrado.");
    }

    public static ArrayList<Predio> listaPredio(int codigoCampus) {

        ArrayList<Predio> p = new ArrayList<Predio>();
        for (Predio c : predios) {
            if (c.getCampus().getCodigo() == codigoCampus) {
                p.add(c);
            }
        }
        return p;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="crud salaReuniao">
    public static boolean consultaSalaReuniao(SalaReuniao salaReuniao) {
        for (Iterator<SalaReuniao> iterator = salaReuniaos.iterator(); iterator.hasNext();) {
            SalaReuniao c = iterator.next();
            if (salaReuniao.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean gravaSalaReuniao(SalaReuniao salaReuniao) throws Exception {
        if (consultaSalaReuniao(salaReuniao)) {
            throw new Exception("SalaReuniao já cadastrada." + salaReuniao);
        }
        salaReuniaos.add(salaReuniao);
        return true;
    }

    public static boolean excluiSalaReuniao(SalaReuniao salaReuniao) throws Exception {
        if (!consultaSalaReuniao(salaReuniao)) {
            throw new Exception("Sala de Reuniao não cadastrada.");
        }
        for (Iterator<SalaReuniao> iterator = salaReuniaos.iterator(); iterator.hasNext();) {
            SalaReuniao c = iterator.next();
            if (salaReuniao.equals(c)) {
                iterator.remove();
                return true;
            }
        }
        return true;
    }

    public static SalaReuniao recuperaSalaReuniao(int codigoSalaReuniao, int codigoPredio,
            int codigoCampus) throws Exception {
        for (Iterator<SalaReuniao> iterator = salaReuniaos.iterator(); iterator.hasNext();) {
            SalaReuniao c = iterator.next();
            if (c.getCodigo() == codigoSalaReuniao && c.getPredio().getCodigo()
                    == codigoPredio && c.getPredio().getCampus().getCodigo() == codigoCampus) {
                return c;
            }
        }
        throw new Exception("Sala de Reuniao não cadastrada.");
    }

    public static ArrayList<SalaReuniao> listaSalaReuniao(int codigoCampus) {

        ArrayList<SalaReuniao> p = new ArrayList<SalaReuniao>();
        for (SalaReuniao c : salaReuniaos) {
            if (c.getPredio().getCampus().getCodigo() == codigoCampus) {
                p.add(c);
            }
        }
        return p;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="crud funcionario">
    public static boolean consultaFuncionario(Funcionario funcionario) {
        for (Iterator<Funcionario> iterator = funcionarios.iterator(); iterator.hasNext();) {
            Funcionario c = iterator.next();
            if (funcionario.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean gravaFuncionario(Funcionario funcionario) throws Exception {
        if (consultaFuncionario(funcionario)) {
            throw new Exception("Funcionario já cadastrado.");
        }
        funcionarios.add(funcionario);
        return true;
    }

    public static boolean excluiFuncionario(Funcionario funcionario) throws Exception {
        if (!consultaFuncionario(funcionario)) {
            throw new Exception("Funcionário não cadastrado.");
        }
        for (Iterator<Funcionario> iterator = funcionarios.iterator(); iterator.hasNext();) {
            Funcionario c = iterator.next();
            if (funcionario.equals(c)) {
                iterator.remove();
                return true;
            }
        }
        return true;
    }

    public static Funcionario recuperaFuncionario(int codigoFuncionario, int codigoCampus) throws Exception {
        for (Iterator<Funcionario> iterator = funcionarios.iterator(); iterator.hasNext();) {
            Funcionario c = iterator.next();
            if (c.getCodigo() == codigoFuncionario && c.getCampus().getCodigo() == codigoCampus) {
                return c;
            }
        }
        throw new Exception("Funcionário não cadastrado.");
    }

    public static ArrayList<Funcionario> listaFuncionario(int codigoCampus) {

        ArrayList<Funcionario> p = new ArrayList<Funcionario>();
        for (Funcionario c : funcionarios) {
            if (c.getCampus().getCodigo() == codigoCampus) {
                p.add(c);
            }
        }
        return p;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="crud equipamento">
    public static boolean consultaEquipamento(Equipamento equipamento) {
        for (Iterator<Equipamento> iterator = equipamentos.iterator(); iterator.hasNext();) {
            Equipamento c = iterator.next();
            if (equipamento.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean gravaEquipamento(Equipamento equipamento) throws Exception {
        if (consultaEquipamento(equipamento)) {
            throw new Exception("Equipamento já cadastrado.");
        }
        equipamentos.add(equipamento);
        return true;
    }

    public static boolean excluiEquipamento(Equipamento equipamento) throws Exception {
        if (!consultaEquipamento(equipamento)) {
            throw new Exception("Equipamento não cadastrado.");
        }
        for (Iterator<Equipamento> iterator = equipamentos.iterator(); iterator.hasNext();) {
            Equipamento c = iterator.next();
            if (equipamento.equals(c)) {
                iterator.remove();
                return true;
            }
        }
        return true;
    }

    public static Equipamento recuperaEquipamento(int codigoEquipamento, int codigoCampus) throws Exception {
        for (Iterator<Equipamento> iterator = equipamentos.iterator(); iterator.hasNext();) {
            Equipamento c = iterator.next();
            if (c.getCodigo() == codigoEquipamento && c.getCampus().getCodigo() == codigoCampus) {
                return c;
            }
        }
        throw new Exception("Equipamento não cadastrado.");
    }

    public static ArrayList<Equipamento> listaEquipamento(int codigoCampus) {

        ArrayList<Equipamento> p = new ArrayList<Equipamento>();
        for (Equipamento c : equipamentos) {
            if (c.getCampus().getCodigo() == codigoCampus) {
                p.add(c);
            }
        }
        return p;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="crud reserva">
    public static boolean consultaReserva(Reserva reserva) {
        for (Iterator<Reserva> iterator = reservas.iterator(); iterator.hasNext();) {
            Reserva c = iterator.next();
            if (reserva.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean gravaReserva(Reserva reserva) throws Exception {
        if (consultaReserva(reserva)) {
            throw new Exception("Reserva já cadastrada.");
        }
        Reserva r = new Reserva(reserva);
        reservas.add(r);
        return true;
    }

    public static boolean excluiReserva(Reserva reserva) throws Exception {

        if (!consultaReserva(reserva)) {
            throw new Exception("Sala não reserva nessa data-horário");
        }

        for (Iterator<Reserva> iterator = reservas.iterator(); iterator.hasNext();) {
            Reserva c = iterator.next();
            if (reserva.equals(c)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static Reserva recuperaReserva(int codigoSalaReuniao, int codigoPredio,
            int codigoCampus, LocalDate dataReserva, LocalTime horaInicio, LocalTime horaFim) throws Exception {
        for (Iterator<Reserva> iterator = reservas.iterator(); iterator.hasNext();) {
            Reserva c = iterator.next();
            if (c.getSalaReuniao().getCodigo() == codigoSalaReuniao
                    && c.getSalaReuniao().getPredio().getCodigo() == codigoPredio
                    && c.getSalaReuniao().getPredio().getCampus().getCodigo() == codigoCampus
                    && c.getDataReserva() == dataReserva
                    && c.getHoraInicio() == horaInicio
                    && c.getHoraFim() == horaFim) {
                return c;
            }
        }
        throw new Exception("Reserva não efetuada para essa data e hora.");
    }

    public static ArrayList<Reserva> listaReserva(int codigoCampus) {

        ArrayList<Reserva> p = new ArrayList<Reserva>();
        for (Reserva c : reservas) {
            if (c.getSalaReuniao().getPredio().getCampus().getCodigo() == codigoCampus) {
                p.add(c);
            }
        }
        return p;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="crud itemEquipamento">
    public static boolean consultaItemEquipamento(ItemEquipamento itemEquipamento) {
        for (Iterator<ItemEquipamento> iterator = itemEquipamentos.iterator(); iterator.hasNext();) {
            ItemEquipamento c = iterator.next();
            if (itemEquipamento.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean gravaItemEquipamento(ItemEquipamento itemEquipamento) throws Exception {
        if (consultaItemEquipamento(itemEquipamento)) {
            throw new Exception("Equipamento já reservado para essa data/horário.");
        }
        ItemEquipamento re = new ItemEquipamento(itemEquipamento);
        itemEquipamentos.add(re);
        return true;
    }

    public static boolean excluiItemEquipamento(ItemEquipamento itemEquipamento) throws Exception {
        if (!consultaItemEquipamento(itemEquipamento)) {
 
            throw new Exception("Equipamento não reservado para essa data/horário.");
        }
        for (Iterator<ItemEquipamento> iterator = itemEquipamentos.iterator(); iterator.hasNext();) {
            ItemEquipamento c = iterator.next();
            if (itemEquipamento.equals(c)) {
                iterator.remove();
                return true;
            }
        }
        return true;
    }

    public static ItemEquipamento recuperaItemEquipamento(int codigo, int codigoSalaReuniao, int codigoPredio,
            int codigoCampus, LocalDate dataReserva, LocalTime horaInicio, LocalTime horaFim) throws Exception {
        for (Iterator<ItemEquipamento> iterator = itemEquipamentos.iterator(); iterator.hasNext();) {
            ItemEquipamento c = iterator.next();
            if (c.getEquipamento().getCodigo() == codigo
                    && c.getReserva().getSalaReuniao().getCodigo() == codigoSalaReuniao
                    && c.getReserva().getSalaReuniao().getPredio().getCodigo() == codigoPredio
                    && c.getReserva().getSalaReuniao().getPredio().getCampus().getCodigo() == codigoCampus
                    && c.getReserva().getDataReserva() == dataReserva
                    && c.getReserva().getHoraInicio() == horaInicio
                    && c.getReserva().getHoraFim() == horaFim) {
                return c;
            }
        }
        throw new Exception("ItemEquipamento não efetuada para essa data e hora.");
    }

    public static ArrayList<ItemEquipamento> listaItemEquipamento(int codigoCampus) {

        ArrayList<ItemEquipamento> p = new ArrayList<ItemEquipamento>();
        for (ItemEquipamento c : itemEquipamentos) {
            if (c.getEquipamento().getCampus().getCodigo()== codigoCampus) {
                p.add(c);
            }
        }
        return p;
    }

//</editor-fold>
}
