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
package com.mycompany.bookaroom.util;

import com.mycompany.bookaroom.cadastro.Campus;
import com.mycompany.bookaroom.cadastro.Endereco;
import com.mycompany.bookaroom.cadastro.Equipamento;
import com.mycompany.bookaroom.cadastro.Funcionario;
import com.mycompany.bookaroom.cadastro.Predio;
import com.mycompany.bookaroom.cadastro.SalaReuniao;
import com.mycompany.bookaroom.reservas.ItemEquipamento;
import com.mycompany.bookaroom.reservas.Reserva;
import com.mycompany.bookaroom.bd.GeradorBD;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class RegistradorReserva {

    private static List<Campus> campuss;
    private Reserva reserva;
    private ItemEquipamento itemEquipamento;
    private GeradorBD geradorBD;
    private static RegistradorReserva instance;

    private RegistradorReserva() throws Exception {
        reserva = new Reserva();
        itemEquipamento = new ItemEquipamento();
        geradorBD = new GeradorBD();
        campuss = geradorBD.load();
        if (campuss.size() > 0) {
            return;
        }
        for (int codigoCampus = 1; codigoCampus < 4; codigoCampus++) {
            Campus campus = new Campus();
            campus.setCodigo(codigoCampus);
            campus.setNome("Campus " + codigoCampus);
            Endereco endereco = new Endereco();
            endereco.setCodigo(codigoCampus);
            endereco.setLogradouro("Rua " + codigoCampus);
            endereco.setNumero(codigoCampus);
            endereco.setCep(37140000L + (long) codigoCampus);
            endereco.setBairro("Bairro" + codigoCampus);
            endereco.setCidade("Cidade" + codigoCampus);
            endereco.setEstado("MG");
            campus.setEndereco(endereco);
            endereco.setCampus(campus);

            RegistradorReserva.gravaCampus(campus);

            for (int codigoFuncionario = 1; codigoFuncionario < 4; codigoFuncionario++) {

                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(codigoFuncionario);
                funcionario.setCampus(campus);
                funcionario.setNome("Funcionário " + codigoFuncionario + " - campus " + codigoCampus);

                RegistradorReserva.gravaFuncionario(funcionario);
//                campus.getFuncionarios().add(funcionario);
            }

            for (int codigoEquipamento = 1; codigoEquipamento < 8; codigoEquipamento++) {
                Equipamento equipamento = new Equipamento();
                equipamento.setCodigo(codigoEquipamento);
                equipamento.setTipo(codigoEquipamento - 1);
                equipamento.setCampus(campus);

                RegistradorReserva.gravaEquipamento(equipamento);
//                campus.getEquipamentos().add(equipamento);
            }

            for (int codigoPredio = 1; codigoPredio < 4; codigoPredio++) {
                Predio predio = new Predio();
                predio.setCodigo(codigoPredio);
                predio.setCampus(campus);
                predio.setNome("Prédio" + codigoPredio + " - Campus " + codigoCampus);

                RegistradorReserva.gravaPredio(predio);
//                campus.getPredios().add(predio);

//              inclui salas para teste
                for (int codigoSalaReuniao = 1; codigoSalaReuniao < 4; codigoSalaReuniao++) {
                    SalaReuniao salaReuniao = new SalaReuniao();
                    salaReuniao.setCodigo(codigoSalaReuniao);
                    salaReuniao.setPredio(predio);
                    salaReuniao.setNumLugares(20 + codigoSalaReuniao + codigoPredio + codigoCampus);

                    RegistradorReserva.gravaSalaReuniao(salaReuniao);
//                    predio.getSalaReuniaos().add(salaReuniao);

                    Reserva reserva = new Reserva();
                    reserva.setDataReserva(LocalDate.now().plusDays(10 * codigoPredio + codigoSalaReuniao));
                    reserva.setHoraInicio(LocalTime.parse("11:00"));
                    reserva.setHoraFim(LocalTime.parse("12:00"));
                    reserva.setSalaReuniao(salaReuniao);
                    reserva.setFuncionario(campus.getFuncionarios().get(1));
                    reserva.setAssunto("Teste " + codigoSalaReuniao);

                    ItemEquipamento itemEquipamento = new ItemEquipamento();
                    itemEquipamento.setReserva(reserva);
                    Equipamento equipamento = new Equipamento();
                    equipamento.setCodigo(codigoSalaReuniao);
                    equipamento.setTipo(codigoSalaReuniao - 1);
                    equipamento.setCampus(campus);
                    itemEquipamento.setEquipamento(equipamento);

                    RegistradorReserva.gravaReserva(reserva);
                    RegistradorReserva.gravaItemEquipamento(itemEquipamento);
//                    reserva.getItemEquipamentos().add(itemEquipamento);
//                    salaReuniao.getReservas().add(reserva);

                }
            }

        }
        geradorBD.save(campuss);
    }

//<editor-fold defaultstate="collapsed" desc="geters and setters">
    public static RegistradorReserva getInstance() throws Exception {
        if (instance == null) {
            instance = new RegistradorReserva();
        }
        return instance;
    }

    public Reserva getReserva() {
        return reserva;

    }

    public void setReserva(Reserva reserva) {
        this.reserva = new Reserva(reserva);
    }

    public ItemEquipamento getItemEquipamento() {
        return itemEquipamento;
    }

    public void setItemEquipamento(ItemEquipamento itemEquipamento) throws Exception {
        this.itemEquipamento = new ItemEquipamento(itemEquipamento);
    }

    public static List<Campus> getCampuss() {
        return campuss;
    }

    public static void setCampuss(List<Campus> campuss) {
        RegistradorReserva.campuss = campuss;
    }

//</editor-fold>
    public boolean gerarReserva() throws Exception {
        if (reserva.isAula() == false) {
            campuss = geradorBD.load();
        }
        if (!RegistradorReserva.consultaSalaReuniao(reserva.getSalaReuniao())) {
            throw new Exception("Sala não cadastrada.");
        }
//data1.compareTo(date2); //data1 < data2, retorna um valor menor que 0
//data2.compareTo(date1); //data2 > data1, retorna um valor maior que 0
//data1.compareTo(date3); //data1 = data3, então um 0 será mostrado no console
        ArrayList<Reserva> reservas
                = RegistradorReserva.listaReserva(reserva.getSalaReuniao().getPredio().getCampus().getCodigo());

        for (Iterator<Reserva> iterator = reservas.iterator(); iterator.hasNext();) {
            Reserva r = iterator.next();
            if (r.getSalaReuniao().getCodigo() == reserva.getSalaReuniao().getCodigo()
                    && r.getSalaReuniao().getPredio().getCodigo()
                    == reserva.getSalaReuniao().getPredio().getCodigo()
                    && r.getSalaReuniao().getPredio().getCampus().getCodigo()
                    == reserva.getSalaReuniao().getPredio().getCampus().getCodigo()) {
                if (reserva.getDataReserva().compareTo(r.getDataReserva()) == 0) {
                    if ((reserva.getHoraInicio().compareTo(r.getHoraInicio()) >= 0
                            && reserva.getHoraInicio().compareTo(r.getHoraFim()) <= 0)
                            || (reserva.getHoraFim().compareTo(r.getHoraInicio()) >= 0
                            && reserva.getHoraFim().compareTo(r.getHoraFim()) <= 0)) {
                        if (reserva.isAula() == true) {
                            cancelarReserva(r);
                            iterator.remove();
                        } else {
                            throw new Exception("Sala já reserva nesse horário");
                        }
                    }
                }
            }
        }
        RegistradorReserva.gravaReserva(reserva);
        if (reserva.isAula() == false) {
            geradorBD.save(campuss);
            System.out.print("Reserva gravada com sucesso.\n");
        }
        return true;

    }

    public boolean cancelarReserva(Reserva r) throws Exception {
        campuss = geradorBD.load();
        ArrayList<ItemEquipamento> itemEquipamentos
                = RegistradorReserva.listaItemEquipamento(r.getSalaReuniao().getPredio().getCampus().getCodigo());
        for (ItemEquipamento c : itemEquipamentos) {
            if (c.getReserva() == r) {
                RegistradorReserva.excluiItemEquipamento(c);
            }
        }
        RegistradorReserva.excluiReserva(r);
        geradorBD.save(campuss);
        System.out.println("Reserva cancelada com sucesso\n");
        return true;
    }

    public boolean gerarItemEquipamento() throws Exception {
        campuss = geradorBD.load();
        if (!RegistradorReserva.consultaEquipamento(itemEquipamento.getEquipamento())) {
            throw new Exception("Equipamento não cadastrado.");
        }
        if (!RegistradorReserva.consultaReserva(itemEquipamento.getReserva())) {
            throw new Exception("Reserva não cadastrada.");
        }
        ArrayList<ItemEquipamento> itemEquipamentos
                = RegistradorReserva.listaItemEquipamento(reserva.getSalaReuniao().getPredio().getCampus().getCodigo());
        for (ItemEquipamento c : itemEquipamentos) {
            if (itemEquipamento.getEquipamento().getCodigo() == c.getEquipamento().getCodigo()) {
                if (c.getReserva().getDataReserva().compareTo(itemEquipamento.getReserva().getDataReserva()) == 0) {
                    if ((itemEquipamento.getReserva().getHoraInicio().compareTo(c.getReserva().getHoraInicio()) >= 0
                            && itemEquipamento.getReserva().getHoraInicio().compareTo(c.getReserva().getHoraFim()) <= 0)
                            || (itemEquipamento.getReserva().getHoraFim().compareTo(c.getReserva().getHoraInicio()) >= 0
                            && itemEquipamento.getReserva().getHoraFim().compareTo(c.getReserva().getHoraFim()) <= 0)) {
                        throw new Exception("Equipamento já reservado nessa data/horário.");

                    }
                }

            }

        }
        RegistradorReserva.gravaItemEquipamento(itemEquipamento);
        geradorBD.save(campuss);
        System.out.println("Equipamento reservado com sucesso");
        return true;
    }

    public boolean cancelarItemEquipamento() throws Exception {
        campuss = geradorBD.load();
        RegistradorReserva.excluiItemEquipamento(itemEquipamento);
        System.out.println("Reserva de equipamento cancelada com sucesso.");
        geradorBD.save(campuss);
        return true;
    }

    public boolean gerarReservaAula(LocalDate dataFimSemestre, int diaSemana) throws Exception {
        campuss = geradorBD.load();
        while (reserva.getDataReserva().getDayOfWeek().getValue() != diaSemana) {
            reserva.setDataReserva(reserva.getDataReserva().plusDays(1));
        }
        while (reserva.getDataReserva().compareTo(dataFimSemestre) <= 0) {
            gerarReserva();
            reserva = new Reserva(reserva);
            reserva.setDataReserva(reserva.getDataReserva().plusDays(7));
        }
        geradorBD.save(campuss);
        System.out.print("Reservas aulas gravadas com sucesso.\n");
        return true;
    }

    public boolean gerarEquipamento(Equipamento e) throws Exception {
        campuss = geradorBD.load();
        RegistradorReserva.gravaEquipamento(e);
        geradorBD.save(campuss);
        System.out.println("Equipamento gravado com sucesso.");
        return true;
    }
//
////<editor-fold defaultstate="collapsed" desc="crud campus">

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
//
//    public static boolean excluiCampus(Campus campus) throws Exception {
//        if (!consultaCampus(campus)) {
//            throw new Exception("Campus não cadastrado.");
//        }
//        for (Iterator<Campus> iterator = campuss.iterator(); iterator.hasNext();) {
//            Campus c = iterator.next();
//            if (campus.equals(c)) {
//                iterator.remove();
//                return true;
//            }
//        }
//        return true;
//    }
//

    public static Campus recuperaCampus(int codigoCampus) throws Exception {
        for (Iterator<Campus> iterator = campuss.iterator(); iterator.hasNext();) {
            Campus c = iterator.next();
            if (c.getCodigo() == codigoCampus) {
                return c;
            }
        }
        throw new Exception("Campus não cadastrado.");
    }
//
////</editor-fold>
////<editor-fold defaultstate="collapsed" desc="crud predio">

    public static boolean consultaPredio(Predio predio) {
        for (Campus c : campuss) {
            if (c.getCodigo() == predio.getCampus().getCodigo()) {
                for (Predio p : c.getPredios()) {
                    if (predio.equals(p)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean gravaPredio(Predio predio) throws Exception {
        if (consultaPredio(predio)) {
            throw new Exception("Predio já cadastrado.");
        }
        for (Campus c : campuss) {
            if (c.getCodigo() == predio.getCampus().getCodigo()) {
                c.getPredios().add(predio);
            }
        }
        return true;
    }

//    public static boolean excluiPredio(Predio predio) throws Exception {
//        if (!consultaPredio(predio)) {
//            throw new Exception("Predio não cadastrado.");
//        }
//        for (Iterator<Predio> iterator = predios.iterator(); iterator.hasNext();) {
//            Predio c = iterator.next();
//            if (predio.equals(c)) {
//                iterator.remove();
//                return true;
//            }
//        }
//        return true;
//    }
//
//    public static Predio recuperaPredio(int codigoPredio, int codigoCampus) throws Exception {
//        for (Iterator<Predio> iterator = predios.iterator(); iterator.hasNext();) {
//            Predio c = iterator.next();
//            if (c.getCodigo() == codigoPredio && c.getCampus().getCodigo() == codigoCampus) {
//                return c;
//            }
//        }
//        throw new Exception("Predio não cadastrado.");
//    }
//
//    public static ArrayList<Predio> listaPredio(int codigoCampus) {
//
//        ArrayList<Predio> p = new ArrayList<Predio>();
//        for (Predio c : predios) {
//            if (c.getCampus().getCodigo() == codigoCampus) {
//                p.add(c);
//            }
//        }
//        return p;
//    }
//
////</editor-fold>
////<editor-fold defaultstate="collapsed" desc="crud salaReuniao">
    public static boolean consultaSalaReuniao(SalaReuniao salaReuniao) {
        for (Campus c : campuss) {
            if (c.getCodigo() == salaReuniao.getPredio().getCampus().getCodigo()) {
                for (Predio p : c.getPredios()) {
                    if (p.getCodigo() == salaReuniao.getPredio().getCodigo()) {
                        for (SalaReuniao s : p.getSalaReuniaos()) {
                            if (salaReuniao.equals(s)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean gravaSalaReuniao(SalaReuniao salaReuniao) throws Exception {
        if (consultaSalaReuniao(salaReuniao)) {
            throw new Exception("SalaReuniao já cadastrada.");
        }
        for (Campus c : campuss) {
            if (c.getCodigo() == salaReuniao.getPredio().getCampus().getCodigo()) {
                for (Predio p : c.getPredios()) {
                    if (p.getCodigo() == salaReuniao.getPredio().getCodigo()) {
                        p.getSalaReuniaos().add(salaReuniao);
                    }
                }
            }
        }
        return true;
    }

//    public static boolean consultaSalaReuniao(SalaReuniao salaReuniao) {
//        for (Iterator<SalaReuniao> iterator = salaReuniaos.iterator(); iterator.hasNext();) {
//            SalaReuniao c = iterator.next();
//            if (salaReuniao.equals(c)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static boolean gravaSalaReuniao(SalaReuniao salaReuniao) throws Exception {
//        if (consultaSalaReuniao(salaReuniao)) {
//            throw new Exception("SalaReuniao já cadastrada." + salaReuniao);
//        }
//        salaReuniaos.add(salaReuniao);
//        return true;
//    }
//
//    public static boolean excluiSalaReuniao(SalaReuniao salaReuniao) throws Exception {
//        if (!consultaSalaReuniao(salaReuniao)) {
//            throw new Exception("Sala de Reuniao não cadastrada.");
//        }
//        for (Iterator<SalaReuniao> iterator = salaReuniaos.iterator(); iterator.hasNext();) {
//            SalaReuniao c = iterator.next();
//            if (salaReuniao.equals(c)) {
//                iterator.remove();
//                return true;
//            }
//        }
//        return true;
//    }
//
//    public static SalaReuniao recuperaSalaReuniao(int codigoSalaReuniao, int codigoPredio,
//            int codigoCampus) throws Exception {
//        for (Iterator<SalaReuniao> iterator = salaReuniaos.iterator(); iterator.hasNext();) {
//            SalaReuniao c = iterator.next();
//            if (c.getCodigo() == codigoSalaReuniao && c.getPredio().getCodigo()
//                    == codigoPredio && c.getPredio().getCampus().getCodigo() == codigoCampus) {
//                return c;
//            }
//        }
//        throw new Exception("Sala de Reuniao não cadastrada.");
//    }
//
    public static ArrayList<SalaReuniao> listaSalaReuniao(int codigoCampus) {
        ArrayList<SalaReuniao> ss = new ArrayList<SalaReuniao>();
        for (Campus c : campuss) {
            if (c.getCodigo() == codigoCampus) {
                for (Predio p : c.getPredios()) {
                    for (SalaReuniao s : p.getSalaReuniaos()) {
                        ss.add(s);
                    }
                }
                return ss;
            }
        }
        return ss;
    }
//
////</editor-fold>
////<editor-fold defaultstate="collapsed" desc="crud funcionario">
//    public static boolean consultaFuncionario(Funcionario funcionario) {
//        for (Iterator<Funcionario> iterator = funcionarios.iterator(); iterator.hasNext();) {
//            Funcionario c = iterator.next();
//            if (funcionario.equals(c)) {
//                return true;
//            }
//        }
//        return false;
//    }
//

    public static boolean consultaFuncionario(Funcionario funcionario) {
        for (Campus c : campuss) {
            if (c.getCodigo() == funcionario.getCampus().getCodigo()) {
                for (Funcionario f : c.getFuncionarios()) {
                    if (funcionario.equals(f)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean gravaFuncionario(Funcionario funcionario) throws Exception {
        if (consultaFuncionario(funcionario)) {
            throw new Exception("Funcionario já cadastrado.");
        }
        for (Campus c : campuss) {
            if (c.getCodigo() == funcionario.getCampus().getCodigo()) {
                c.getFuncionarios().add(funcionario);
            }
        }
        return true;
    }
//

    public static Funcionario recuperaFuncionario(int codigoFuncionario, int codigoCampus) throws Exception {
        for (Campus c : campuss) {
            if (c.getCodigo() == codigoCampus) {
                for (Funcionario f : c.getFuncionarios()) {
                    if (f.getCodigo() == codigoFuncionario) {
                        return f;
                    }
                }
            }
        }
        throw new Exception("Funcionário não cadastrado.");
    }
//
//    public static ArrayList<Funcionario> listaFuncionario(int codigoCampus) {
//
//        ArrayList<Funcionario> p = new ArrayList<Funcionario>();
//        for (Funcionario c : funcionarios) {
//            if (c.getCampus().getCodigo() == codigoCampus) {
//                p.add(c);
//            }
//        }
//        return p;
//    }
//
////</editor-fold>
////<editor-fold defaultstate="collapsed" desc="crud equipamento">

    public static boolean consultaEquipamento(Equipamento equipamento) {
        for (Campus c : campuss) {
            if (c.getCodigo() == equipamento.getCampus().getCodigo()) {
                for (Equipamento e : c.getEquipamentos()) {
                    if (equipamento.equals(e)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean gravaEquipamento(Equipamento equipamento) throws Exception {
        if (consultaEquipamento(equipamento)) {
            throw new Exception("Equipamento já cadastrado.");
        }
        for (Campus c : campuss) {
            if (c.getCodigo() == equipamento.getCampus().getCodigo()) {
                c.getEquipamentos().add(equipamento);
            }
        }
        return true;
    }
//
//    public static boolean excluiEquipamento(Equipamento equipamento) throws Exception {
//        if (!consultaEquipamento(equipamento)) {
//            throw new Exception("Equipamento não cadastrado.");
//        }
//        for (Iterator<Equipamento> iterator = equipamentos.iterator(); iterator.hasNext();) {
//            Equipamento c = iterator.next();
//            if (equipamento.equals(c)) {
//                iterator.remove();
//                return true;
//            }
//        }
//        return true;
//    }
//
//    public static Equipamento recuperaEquipamento(int codigoEquipamento, int codigoCampus) throws Exception {
//        for (Iterator<Equipamento> iterator = equipamentos.iterator(); iterator.hasNext();) {
//            Equipamento c = iterator.next();
//            if (c.getCodigo() == codigoEquipamento && c.getCampus().getCodigo() == codigoCampus) {
//                return c;
//            }
//        }
//        throw new Exception("Equipamento não cadastrado.");
//    }
//
//    public static ArrayList<Equipamento> listaEquipamento(int codigoCampus) {
//
//        ArrayList<Equipamento> p = new ArrayList<Equipamento>();
//        for (Equipamento c : equipamentos) {
//            if (c.getCampus().getCodigo() == codigoCampus) {
//                p.add(c);
//            }
//        }
//        return p;
//    }
//
////</editor-fold>
////<editor-fold defaultstate="collapsed" desc="crud reserva">

    public static boolean consultaReserva(Reserva reserva) {
        for (Campus c : campuss) {
            if (c.getCodigo() == reserva.getSalaReuniao().getPredio().getCampus().getCodigo()) {
                for (Predio p : c.getPredios()) {
                    if (p.getCodigo() == reserva.getSalaReuniao().getPredio().getCodigo()) {
                        for (SalaReuniao s : p.getSalaReuniaos()) {
                            if (s.getCodigo() == reserva.getSalaReuniao().getCodigo()) {
                                for (Reserva r : s.getReservas()) {
                                    if (reserva.equals(r)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
//

    public static boolean gravaReserva(Reserva reserva) throws Exception {
        if (consultaReserva(reserva)) {
            throw new Exception("Reserva já cadastrada.");
        }
        for (Campus c : campuss) {
            if (c.getCodigo() == reserva.getSalaReuniao().getPredio().getCampus().getCodigo()) {
                for (Predio p : c.getPredios()) {
                    if (p.getCodigo() == reserva.getSalaReuniao().getPredio().getCodigo()) {
                        for (SalaReuniao s : p.getSalaReuniaos()) {
                            if (s.getCodigo() == reserva.getSalaReuniao().getCodigo()) {
                                s.getReservas().add(reserva);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
//

    public static boolean excluiReserva(Reserva reserva) throws Exception {

        if (!consultaReserva(reserva)) {
            throw new Exception("Sala não reserva nessa data-horário");
        }

        for (Campus c : campuss) {
            if (c.getCodigo() == reserva.getSalaReuniao().getPredio().getCampus().getCodigo()) {
                for (Predio p : c.getPredios()) {
                    if (p.getCodigo() == reserva.getSalaReuniao().getPredio().getCodigo()) {
                        for (SalaReuniao s : p.getSalaReuniaos()) {
                            if (s.getCodigo() == reserva.getSalaReuniao().getCodigo()) {
                                for (Iterator<Reserva> iterator = s.getReservas().iterator(); iterator.hasNext();) {
                                    Reserva r = iterator.next();
                                    if (reserva.equals(r)) {
                                        iterator.remove();
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

//    public static Reserva recuperaReserva(int codigoSalaReuniao, int codigoPredio,
//            int codigoCampus, LocalDate dataReserva, LocalTime horaInicio, LocalTime horaFim) throws Exception {
//        for (Iterator<Reserva> iterator = reservas.iterator(); iterator.hasNext();) {
//            Reserva c = iterator.next();
//            if (c.getSalaReuniao().getCodigo() == codigoSalaReuniao
//                    && c.getSalaReuniao().getPredio().getCodigo() == codigoPredio
//                    && c.getSalaReuniao().getPredio().getCampus().getCodigo() == codigoCampus
//                    && c.getDataReserva() == dataReserva
//                    && c.getHoraInicio() == horaInicio
//                    && c.getHoraFim() == horaFim) {
//                return c;
//            }
//        }
//        throw new Exception("Reserva não efetuada para essa data e hora.");
//    }
//
    public static ArrayList<Reserva> listaReserva(int codigoCampus) {

        ArrayList<Reserva> rs = new ArrayList<Reserva>();
        for (Campus c : campuss) {
            if (c.getCodigo() == codigoCampus) {
                for (Predio p : c.getPredios()) {
                    for (SalaReuniao s : p.getSalaReuniaos()) {
                        for (Reserva r : s.getReservas()) {
                            rs.add(r);
                        }
                    }
                }
            }
        }
        return rs;
    }
//
////</editor-fold>
////<editor-fold defaultstate="collapsed" desc="crud itemEquipamento">

    public static boolean consultaItemEquipamento(ItemEquipamento itemEquipamento) {
        for (Campus c : campuss) {
            if (c.getCodigo() == itemEquipamento.getReserva().getSalaReuniao().getPredio().getCampus().getCodigo()) {
                for (Predio p : c.getPredios()) {
                    for (SalaReuniao s : p.getSalaReuniaos()) {
                        for (Reserva r : s.getReservas()) {
                            if (r.getDataReserva().compareTo(itemEquipamento.getReserva().getDataReserva()) == 0) {
                                if ((r.getHoraInicio().compareTo(itemEquipamento.getReserva().getHoraInicio()) >= 0
                                        && r.getHoraInicio().compareTo(itemEquipamento.getReserva().getHoraFim()) <= 0)
                                        || (r.getHoraFim().compareTo(itemEquipamento.getReserva().getHoraInicio()) >= 0
                                        && r.getHoraFim().compareTo(itemEquipamento.getReserva().getHoraFim()) <= 0)) {
                                    for (ItemEquipamento ie : r.getItemEquipamentos()) {
                                        if (ie.getEquipamento().getCodigo() == itemEquipamento.getEquipamento().getCodigo()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
//

    public static boolean gravaItemEquipamento(ItemEquipamento itemEquipamento) throws Exception {
        if (consultaItemEquipamento(itemEquipamento)) {
            throw new Exception("Equipamento já reservado para essa data/horário.");
        }
        for (Campus c : campuss) {
            if (c.getCodigo() == itemEquipamento.getReserva().getSalaReuniao().getPredio().getCampus().getCodigo()) {
                for (Predio p : c.getPredios()) {
                    if (p.getCodigo() == itemEquipamento.getReserva().getSalaReuniao().getPredio().getCodigo()) {
                        for (SalaReuniao s : p.getSalaReuniaos()) {
                            if (s.getCodigo() == itemEquipamento.getReserva().getSalaReuniao().getCodigo()) {
                                for (Reserva r : s.getReservas()) {
                                    if (r.equals(itemEquipamento.getReserva())) {
                                        r.getItemEquipamentos().add(itemEquipamento);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
//

    public static boolean excluiItemEquipamento(ItemEquipamento itemEquipamento) throws Exception {
        if (!consultaItemEquipamento(itemEquipamento)) {
            throw new Exception("Equipamento não reservado para essa data/horário.");
        }
        for (Campus c : campuss) {
            if (c.getCodigo() == itemEquipamento.getReserva().getSalaReuniao().getPredio().getCampus().getCodigo()) {
                for (Predio p : c.getPredios()) {
                    if (p.getCodigo() == itemEquipamento.getReserva().getSalaReuniao().getPredio().getCodigo()) {
                        for (SalaReuniao s : p.getSalaReuniaos()) {
                            if (s.getCodigo() == itemEquipamento.getReserva().getSalaReuniao().getCodigo()) {
                                for (Reserva r : s.getReservas()) {
                                    if (r.equals(itemEquipamento.getReserva())) {
                                        for (Iterator<ItemEquipamento> iterator = r.getItemEquipamentos().iterator(); iterator.hasNext();) {
                                            ItemEquipamento ie = iterator.next();
                                            if (itemEquipamento.equals(ie)) {
                                                iterator.remove();
                                                return true;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
//
//    public static ItemEquipamento recuperaItemEquipamento(int codigo, int codigoSalaReuniao, int codigoPredio,
//            int codigoCampus, LocalDate dataReserva, LocalTime horaInicio, LocalTime horaFim) throws Exception {
//        for (Iterator<ItemEquipamento> iterator = itemEquipamentos.iterator(); iterator.hasNext();) {
//            ItemEquipamento c = iterator.next();
//            if (c.getEquipamento().getCodigo() == codigo
//                    && c.getReserva().getSalaReuniao().getCodigo() == codigoSalaReuniao
//                    && c.getReserva().getSalaReuniao().getPredio().getCodigo() == codigoPredio
//                    && c.getReserva().getSalaReuniao().getPredio().getCampus().getCodigo() == codigoCampus
//                    && c.getReserva().getDataReserva() == dataReserva
//                    && c.getReserva().getHoraInicio() == horaInicio
//                    && c.getReserva().getHoraFim() == horaFim) {
//                return c;
//            }
//        }
//        throw new Exception("ItemEquipamento não efetuada para essa data e hora.");
//    }
//

    public static ArrayList<ItemEquipamento> listaItemEquipamento(int codigoCampus) {

        ArrayList<ItemEquipamento> ies = new ArrayList<ItemEquipamento>();

        for (Campus c : campuss) {
            if (c.getCodigo() == codigoCampus) {
                for (Predio p : c.getPredios()) {
                    for (SalaReuniao s : p.getSalaReuniaos()) {
                        for (Reserva r : s.getReservas()) {
                            for (ItemEquipamento ie : r.getItemEquipamentos()) {
                                ies.add(ie);
                            }
                        }
                    }
                }
            }
        }
        return ies;
    }
//
////</editor-fold>

//    
}
