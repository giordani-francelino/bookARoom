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

import com.mycompany.bookaroom.bd.Repositorio;
import com.mycompany.bookaroom.cadastro.Campus;
import com.mycompany.bookaroom.cadastro.Endereco;
import com.mycompany.bookaroom.cadastro.Equipamento;
import com.mycompany.bookaroom.cadastro.Funcionario;
import com.mycompany.bookaroom.cadastro.Predio;
import com.mycompany.bookaroom.cadastro.SalaReuniao;
import com.mycompany.bookaroom.negocio.ItemEquipamento;
import com.mycompany.bookaroom.negocio.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class RegistradorReserva {
    public RegistradorReserva() throws Exception {
        if (primeiroObjeto) {
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

            Repositorio.gravaCampus(campus);

            for (int codigoEquipamento = 1; codigoEquipamento < 8; codigoEquipamento++) {
                Equipamento equipamento = new Equipamento();
                equipamento.setCodigo(codigoEquipamento);
                equipamento.setTipo(codigoEquipamento - 1);
                equipamento.setCampus(campus);

                Repositorio.gravaEquipamento(equipamento);
                campus.getEquipamentos().add(equipamento);
            }

            for (int codigoPredio = 1; codigoPredio < 4; codigoPredio++) {
                Predio predio = new Predio();
                predio.setCodigo(codigoPredio);
                predio.setCampus(campus);
                predio.setNome("Prédio" + codigoPredio + " - Campus " + codigoCampus);

                Repositorio.gravaPredio(predio);
                campus.getPredios().add(predio);

                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(codigoPredio);
                funcionario.setCampus(campus);
                funcionario.setNome("Funcionário " + codigoPredio + " - campus " + codigoCampus);

                Repositorio.gravaFuncionario(funcionario);
                campus.getFuncionarios().add(funcionario);

//              inclui salas para teste
                for (int codigoSalaReuniao = 1; codigoSalaReuniao < 4; codigoSalaReuniao++) {
                    SalaReuniao salaReuniao = new SalaReuniao();
                    salaReuniao.setCodigo(codigoSalaReuniao);
                    salaReuniao.setPredio(predio);
                    salaReuniao.setNumLugares(20 + codigoSalaReuniao + codigoPredio + codigoCampus);

                    Repositorio.gravaSalaReuniao(salaReuniao);
                    predio.getSalaReuniaos().add(salaReuniao);

                    Reserva reserva = new Reserva();
                    reserva.setDataReserva(LocalDate.now().plusDays(10 * codigoPredio + codigoSalaReuniao));
                    reserva.setHoraInicio(LocalTime.parse("11:00"));
                    reserva.setHoraFim(LocalTime.parse("12:00"));
                    reserva.setSalaReuniao(salaReuniao);
                    reserva.setFuncionario(funcionario);
                    reserva.setAssunto("Teste " + codigoSalaReuniao);

                    ItemEquipamento itemEquipamento = new ItemEquipamento();
                    itemEquipamento.setReserva(reserva);
                    Equipamento equipamento = new Equipamento();
                    equipamento.setCodigo(codigoSalaReuniao);
                    equipamento.setTipo(codigoSalaReuniao - 1);
                    equipamento.setCampus(campus);
                    itemEquipamento.setEquipamento(equipamento);

                    Repositorio.gravaReserva(reserva);
                    Repositorio.gravaItemEquipamento(itemEquipamento);
                    reserva.getItemEquipamentos().add(itemEquipamento);
                    salaReuniao.getReservas().add(reserva);

                }
            }

        }
        primeiroObjeto = true;
    }
    private static boolean primeiroObjeto = false;
    private static ArrayList<Campus> campuss = new ArrayList<Campus>();

    private Reserva reserva;
    private ItemEquipamento itemEquipamento;

//<editor-fold defaultstate="collapsed" desc="geters and setters">
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

//</editor-fold>
    public boolean gerarReserva() throws Exception {
        if (!Repositorio.consultaSalaReuniao(reserva.getSalaReuniao())) {
            throw new Exception("Sala não cadastrada.");
        }

//data1.compareTo(date2); //data1 < data2, retorna um valor menor que 0
//data2.compareTo(date1); //data2 > data1, retorna um valor maior que 0
//data1.compareTo(date3); //data1 = data3, então um 0 será mostrado no console
        ArrayList<Reserva> reservas
                = Repositorio.listaReserva(reserva.getSalaReuniao().getPredio().getCampus().getCodigo());

        for (Iterator<Reserva> iterator = reservas.iterator(); iterator.hasNext();) {
            Reserva c = iterator.next();
            if (c.getSalaReuniao().getCodigo() == reserva.getSalaReuniao().getCodigo()
                    && c.getSalaReuniao().getPredio().getCodigo()
                    == reserva.getSalaReuniao().getPredio().getCodigo()
                    && c.getSalaReuniao().getPredio().getCampus().getCodigo()
                    == reserva.getSalaReuniao().getPredio().getCampus().getCodigo()) {
                if (reserva.getDataReserva().compareTo(c.getDataReserva()) == 0) {
                    if ((reserva.getHoraInicio().compareTo(c.getHoraInicio()) >= 0
                            && reserva.getHoraInicio().compareTo(c.getHoraFim()) <= 0)
                            || (reserva.getHoraFim().compareTo(c.getHoraInicio()) >= 0
                            && reserva.getHoraFim().compareTo(c.getHoraFim()) <= 0)) {
                        if (reserva.isAula() == true) {
                            iterator.remove();
                        } else {
                            throw new Exception("Sala já reserva nesse horário");
                        }
                    }
                }
            }
        }
        Repositorio.gravaReserva(reserva);
        if (reserva.isAula() == false) {
            System.out.print("Reserva gravada com sucesso.\n");
        }
        return true;

    }

    public boolean cancelarReserva() throws Exception {
//ArrayList<ItemEquipamento> listaItemEquipamento(int codigoCampus)
        ArrayList<ItemEquipamento> itemEquipamentos
                = Repositorio.listaItemEquipamento(reserva.getSalaReuniao().getPredio().getCampus().getCodigo());
        for (ItemEquipamento c : itemEquipamentos) {
            if (c.getReserva() == reserva) {
                Repositorio.excluiItemEquipamento(c);

            }
        }
        Repositorio.excluiReserva(reserva);
        System.out.println("Reserva cancelada com sucesso\n");
        return true;
    }

    public boolean gerarItemEquipamento() throws Exception {
        if (!Repositorio.consultaEquipamento(itemEquipamento.getEquipamento())) {
            throw new Exception("Equipamento não cadastrado.");
        }
        if (!Repositorio.consultaReserva(itemEquipamento.getReserva())) {
            throw new Exception("Reserva não cadastrada.");
        }
        ArrayList<ItemEquipamento> itemEquipamentos
                = Repositorio.listaItemEquipamento(reserva.getSalaReuniao().getPredio().getCampus().getCodigo());
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
        Repositorio.gravaItemEquipamento(itemEquipamento);
        System.out.println("Equipamento reservado com sucesso");
        return true;
    }

    public boolean cancelarItemEquipamento() throws Exception {
        Repositorio.excluiItemEquipamento(itemEquipamento);
        System.out.println("Reserva de equipamento cancelada com sucesso.");

        return true;
    }

    public boolean gerarReservaAula(LocalDate dataFimSemestre, int diaSemana) throws Exception {

        while (reserva.getDataReserva().getDayOfWeek().getValue() != diaSemana) {
            reserva.setDataReserva(reserva.getDataReserva().plusDays(1));
        }
        while (reserva.getDataReserva().compareTo(dataFimSemestre) <= 0) {
            gerarReserva();
            reserva.setDataReserva(reserva.getDataReserva().plusDays(7));
        }

        return true;
    }

//    
}
