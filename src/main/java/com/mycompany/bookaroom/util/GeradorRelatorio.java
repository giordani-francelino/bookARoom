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

import com.mycompany.bookaroom.bd.GeradorBD;
import com.mycompany.bookaroom.reservas.ItemEquipamento;
import com.mycompany.bookaroom.reservas.Reserva;
import com.mycompany.bookaroom.cadastro.SalaReuniao;
import com.mycompany.bookaroom.cadastro.Campus;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class GeradorRelatorio {

    private GeradorBD geradorBD;
    private List<SalaReuniao> salaReuniaos;
    private List<Reserva> reservas;
//    private List<ItemEquipamento> itemEquipamentos = new ArrayList<ItemEquipamento>();

    public GeradorRelatorio() {
        this.geradorBD = new GeradorBD();
        this.salaReuniaos = new ArrayList<SalaReuniao>();
        this.reservas = new ArrayList<Reserva>();

    }

//    public List<Reserva> reservasAtivas(Campus campus) {
    public void reservasAtivas(Campus campus) {
        RegistradorReserva.setCampuss(geradorBD.load());
        reservas = RegistradorReserva.listaReserva(campus.getCodigo());
        Collections.sort(reservas);
        List<Reserva> r = new ArrayList<Reserva>();
        for (Reserva c : reservas) {
            if ((c.getDataReserva().compareTo(LocalDate.now()) == 0
                    && c.getHoraFim().compareTo(LocalTime.now()) >= 0)
                    || c.getDataReserva().compareTo(LocalDate.now()) > 0) {
                r.add(c);
            }
        }
        for (Reserva c : r) {
            System.out.println(c);
        }
//        return r;
    }

    public void reservasInativas(Campus campus) {
        RegistradorReserva.setCampuss(geradorBD.load());
        reservas = RegistradorReserva.listaReserva(campus.getCodigo());
        Collections.sort(reservas);
        List<Reserva> r = new ArrayList<Reserva>();
        for (Reserva c : reservas) {
            if ((c.getDataReserva().compareTo(LocalDate.now()) == 0
                    && c.getHoraFim().compareTo(LocalTime.now()) >= 0)
                    || c.getDataReserva().compareTo(LocalDate.now()) > 0) {
            } else {
                r.add(c);
            }
        }
        for (Reserva c : r) {
            System.out.println(c);
        }

    }

//    public List<Reserva> salasOcupadas(Campus campus) {
    public void salasOcupadas(Campus campus) {
        RegistradorReserva.setCampuss(geradorBD.load());
        salaReuniaos = RegistradorReserva.listaSalaReuniao(campus.getCodigo());
        Collections.sort(salaReuniaos);
        reservas = RegistradorReserva.listaReserva(campus.getCodigo());
        Collections.sort(reservas);

        for (SalaReuniao s : salaReuniaos) {
            int i = 0;
            for (Reserva r : reservas) {
                if (s.equals(r.getSalaReuniao())) {
                    if ((r.getDataReserva().compareTo(LocalDate.now()) == 0
                            && r.getHoraFim().compareTo(LocalTime.now()) >= 0)
                            || r.getDataReserva().compareTo(LocalDate.now()) > 0) {
                        if (i == 0) {
                            System.out.println("Campus: " + r.getSalaReuniao().getPredio().getCampus().getCodigo()
                                    + " Predio: " + r.getSalaReuniao().getPredio().getCodigo()
                                    + " Sala: " + r.getSalaReuniao().getCodigo()
                                    + " Ocupada: "
                            );

                        }
                        System.out.println("     Data " + r.getDataReserva()
                                + " Hora início " + r.getHoraInicio()
                                + " Hora fim " + r.getHoraFim()
                        );
                        i++;
                    }
                }
            }
        }

//        return reservas;
    }

    public void salasLivres(Campus campus) {
        RegistradorReserva.setCampuss(geradorBD.load());
        salaReuniaos = RegistradorReserva.listaSalaReuniao(campus.getCodigo());
        Collections.sort(salaReuniaos);
//        reservas = new ArrayList<Reserva>();
        reservas = RegistradorReserva.listaReserva(campus.getCodigo());
        Collections.sort(reservas);
        List<Reserva> ra = new ArrayList<Reserva>();
        for (Reserva c : reservas) {
            if ((c.getDataReserva().compareTo(LocalDate.now()) == 0
                    && c.getHoraFim().compareTo(LocalTime.now()) >= 0)
                    || c.getDataReserva().compareTo(LocalDate.now()) > 0) {
                ra.add(c);
            }
        }
        reservas = ra;
        List<Reserva> r = new ArrayList<Reserva>();

        for (int i = 0; i < salaReuniaos.size(); i++) {
            r.add(new Reserva());
            r.get(r.size() - 1).setSalaReuniao(salaReuniaos.get(i));
            r.get(r.size() - 1).setDataReserva(LocalDate.now());

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime horaAtual = LocalTime.now();
            String horaFormatada = fmt.format(horaAtual);

            r.get(r.size() - 1).setHoraInicio(LocalTime.parse(horaFormatada));

            r.get(r.size() - 1).setHoraFim(LocalTime.parse("23:59", DateTimeFormatter.ofPattern("HH:mm")));
            for (Reserva c : reservas) {
                if (salaReuniaos.get(i).getCodigo() == c.getSalaReuniao().getCodigo()
                        && salaReuniaos.get(i).getPredio().getCodigo() == c.getSalaReuniao().getPredio().getCodigo()
                        && salaReuniaos.get(i).getPredio().getCampus().getCodigo() == c.getSalaReuniao().getPredio().getCampus().getCodigo()) {
                    while (c.getDataReserva().compareTo(r.get(r.size() - 1).getDataReserva()) > 0) {
                        r.add(new Reserva());
                        r.get(r.size() - 1).setSalaReuniao(salaReuniaos.get(i));
                        r.get(r.size() - 1).setDataReserva(r.get(r.size() - 2).getDataReserva().plusDays(1));
                        r.get(r.size() - 1).setHoraInicio(LocalTime.parse("00:00", DateTimeFormatter.ofPattern("HH:mm")));
                        r.get(r.size() - 1).setHoraFim(LocalTime.parse("23:59", DateTimeFormatter.ofPattern("HH:mm")));

                    }
                    r.get(r.size() - 1).setHoraFim(c.getHoraInicio());
                    r.add(new Reserva());
                    r.get(r.size() - 1).setSalaReuniao(salaReuniaos.get(i));
                    r.get(r.size() - 1).setDataReserva(c.getDataReserva());
                    r.get(r.size() - 1).setHoraInicio(c.getHoraFim());
                    r.get(r.size() - 1).setHoraFim(LocalTime.parse("23:59", DateTimeFormatter.ofPattern("HH:mm")));
                }

            }
        }
        for (int i = 0; i < r.size(); i++) {
            if (i == 0) {
                System.out.println("Campus: " + r.get(i).getSalaReuniao().getPredio().getCampus().getCodigo()
                        + " Predio: " + r.get(i).getSalaReuniao().getPredio().getCodigo()
                        + " Sala: " + r.get(i).getSalaReuniao().getCodigo()
                        + " Livre: "
                );
            } else {
                if (r.get(i).getSalaReuniao() != r.get(i - 1).getSalaReuniao()) {
                    System.out.println("     Após " + r.get(i - 1).getDataReserva());
                    System.out.println("Campus: " + r.get(i).getSalaReuniao().getPredio().getCampus().getCodigo()
                            + " Predio: " + r.get(i).getSalaReuniao().getPredio().getCodigo()
                            + " Sala: " + r.get(i).getSalaReuniao().getCodigo()
                            + " Livre: "
                    );

                }
            }
            System.out.println("     Data " + r.get(i).getDataReserva()
                    + " Hora início " + r.get(i).getHoraInicio()
                    + " Hora fim " + r.get(i).getHoraFim()
            );
        }
        System.out.println("     Após " + r.get(r.size() - 1).getDataReserva());

    }

}
