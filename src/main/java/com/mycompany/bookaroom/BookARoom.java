/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.bookaroom;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Caixa
 */
public class BookARoom {

    private static BancoDeDados bd = new BancoDeDados();
    private static Relatorio relatorio = new Relatorio();
    private static Equipamento equipamento = new Equipamento();
    private static Funcionario funcionario = new Funcionario();
    private static SalaReuniao salaReuniao = new SalaReuniao();
    private static RegistradorReserva registradorReserva = new RegistradorReserva();
    private static Reserva reserva = new Reserva();
    private static ReservaEquipamento reservaEquipamento = new ReservaEquipamento();
    private static int codigoPredio = 0;
    private static int codigoSalaReuniao = 0;
    private static LocalDate dataReserva = LocalDate.now();
    private static LocalTime horaInicio = LocalTime.now();
    private static LocalTime horaFim = LocalTime.now();
    private static Campus campus = new Campus();
    private static int codigoEquipamento = 0;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        funcionario.setCodigo(1);
        campus.setCodigo(1);

        String s;
//        Scanner sc = new Scanner(System.in);
//<editor-fold defaultstate="collapsed" desc="valida funcionário">

// valida funcionario
//        System.out.print("Digite o codigo do Campus:\n");
//        s = sc.next();
//        campus = Integer.parseInt(s);
//        System.out.print("Digite o codigo do Funcionário:\n");
//        s = sc.next();
//        funcionario.setCodigo(Integer.parseInt(s));
        funcionario.getCampus().setCodigo(campus.getCodigo());
        try {
            funcionario = BancoDeDados.recuperaFuncionario(funcionario.getCodigo(), campus.getCodigo());
        } catch (Exception ex) {
            System.out.println("Funcionário não cadstrado.");
            return;
        }
        System.out.println(funcionario.getNome());

//        if (!BancoDeDados.consultaFuncionario(f)) {
//            System.out.println("Funcionário não cadstrado.");
//            return;
//        }
// valida funcionário
//</editor-fold>
        menuPrincipal();
        while (sc.hasNext()) {
            s = sc.next();

            if (Integer.parseInt(s) == 0) {
                break;
            } else if (Integer.parseInt(s) == 1) {
                // efetuar reserva
                obterDadosReserva();
                String assunto;
                System.out.print("Digite o assunto da reserva:\n");
                assunto = sc.next();
                reserva.setAssunto(assunto);
                try {
                    registradorReserva.setReserva(reserva);
                    registradorReserva.gerarReserva();
                    System.out.print("Reserva gravada com sucesso.\n");
                    System.out.println(reserva + "\n");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (Integer.parseInt(s) == 2) {
                // cancelar reserva 
                obterDadosReserva();

                try {
                    registradorReserva.setReserva(reserva);
                    registradorReserva.cancelarReserva();
                    System.out.println("Reserva cancelada com sucesso\n");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (Integer.parseInt(s) == 3) {
                System.out.println("Digite os dados da reserva:\n");
                obterDadosReserva();
                registarEquipamentos();
            } else if (Integer.parseInt(s) == 4) {
//salas livres
                relatorio.salasLivres(campus);
  
            } else if (Integer.parseInt(s) == 5) {
// salas ocupadas
                relatorio.reservasAtivas(campus);

            } else if (Integer.parseInt(s) == 6) {
//reservas ativas
                relatorio.reservasAtivas(campus);

            } else if (Integer.parseInt(s) == 7) {
// reservas inativas
                relatorio.reservasInativas(campus);
            } else {
                break;
            }
            menuPrincipal();

        }

        sc.close(); //Encerra o programa

//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
//Date data = formato.parse("23/11/2015");
    }

    public static void menuPrincipal() {
        System.out.print("Selecione uma das opções abaixo:\n");
        System.out.print("1 - Efetuar reserva\n");
        System.out.print("2 - Cancelar reserva\n");
        System.out.print("3 - Reserva de Equipamento...\n");
        System.out.print("4 - Salas livres\n");
        System.out.print("5 - Salas ocupadas\n");
        System.out.print("6 - Reservas ativas\n");
        System.out.print("7 - Reservas inativas\n");
        System.out.print("0 - Sair\n");

    }

    public static void subMenu() {
        System.out.print("Selecione uma das opções abaixo:\n");
        System.out.print("1 - Reserva equipamento\n");
        System.out.print("2 - Cancela reserva de equipamento\n");
        System.out.print("0 - Retorna ao menu principal\n");

    }

    public static void obterDadosReserva() {
        String s;

        salaReuniao = new SalaReuniao();
//        reserva = new Reserva();
        System.out.print("Digite o predio:\n");
        s = sc.next();
        codigoPredio = Integer.parseInt(s);
        System.out.print("Digite a sala:\n");
        s = sc.next();
        codigoSalaReuniao = Integer.parseInt(s);
        System.out.print("Digite a data da reserva no formato 'aaaa-mm-dd':\n");
        s = sc.next();
        dataReserva = LocalDate.parse(s);
        System.out.print("Digite a hora de início no formato 'hh:mm':\n");
        s = sc.next();
        horaInicio = LocalTime.parse(s);
        System.out.print("Digite a hora final no formato 'hh:mm':\n");
        s = sc.next();
        horaFim = LocalTime.parse(s);
        salaReuniao.setCodigo(codigoSalaReuniao);
        salaReuniao.getPredio().setCodigo(codigoPredio);
        salaReuniao.getPredio().getCampus().setCodigo(campus.getCodigo());
        reserva.setSalaReuniao(salaReuniao);
        reserva.setDataReserva(dataReserva);
        reserva.setHoraInicio(horaInicio);
        reserva.setHoraFim(horaFim);
        reserva.setFuncionario(funcionario);
    }

    public static void registarEquipamentos() {
        String s;
        if (!BancoDeDados.consultaReserva(reserva)) {
            System.out.println("Reserva não cadastrada.");
            return;
        }

        subMenu();
        while (sc.hasNext()) {
            s = sc.next();
            if (Integer.parseInt(s) == 1) {
                try {
                    obterDadosEquipamento();
                    registradorReserva.gerarReservaEquipamento();
                    System.out.println("Equipamento reservado com sucesso");

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (Integer.parseInt(s) == 2) {
                try {
                    obterDadosEquipamento();
                    registradorReserva.cancelarReservaEquipamento();
                    System.out.println("Reserva de equipamento cancelada.");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                break;
            }
            subMenu();
        }

    }

    public static void obterDadosEquipamento() {
        String s;
//        reservaEquipamento = new ReservaEquipamento();
        System.out.print("Digite o código do equipamento:\n");
        s = sc.next();
        codigoEquipamento = Integer.parseInt(s);
        equipamento.setCodigo(codigoEquipamento);
        equipamento.getCampus().setCodigo(campus.getCodigo());
        reservaEquipamento.setEquipamento(equipamento);
        reservaEquipamento.setReserva(reserva);
        registradorReserva.setReservaEquipamento(reservaEquipamento);
    }

}
