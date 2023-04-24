package com.mycompany.bookaroom;

import com.mycompany.bookaroom.negocio.ItemEquipamento;
import com.mycompany.bookaroom.negocio.Reserva;
import com.mycompany.bookaroom.cadastro.Equipamento;
import com.mycompany.bookaroom.cadastro.Funcionario;
import com.mycompany.bookaroom.cadastro.SalaReuniao;
import com.mycompany.bookaroom.cadastro.Campus;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 *
 * @author Caixa
 */
public class Console {

    private Repositorio bd = new Repositorio();
    private GeradorRelatorio relatorio = new GeradorRelatorio();
    private Equipamento equipamento = new Equipamento();
    private Funcionario funcionario = new Funcionario();
    private SalaReuniao salaReuniao = new SalaReuniao();
    private RegistradorReserva registradorReserva = new RegistradorReserva();
    private Reserva reserva = new Reserva();
    private ItemEquipamento itemEquipamento = new ItemEquipamento();
    private int codigoPredio = 0;
    private int codigoSalaReuniao = 0;
    private LocalDate dataReserva = LocalDate.now();
    private LocalTime horaInicio = LocalTime.now();
    private LocalTime horaFim = LocalTime.now();
    private Campus campus = new Campus();
    private int codigoEquipamento = 0;
    private boolean aula;
    private int diaSemana;
    private LocalDate dataFimSemestre;
    private Scanner sc = new Scanner(System.in);

    public Console() {

    }

    public void abreMenu() {

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
        try {
            campus = Repositorio.recuperaCampus(1);
            funcionario = Repositorio.recuperaFuncionario(1, 1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

//        if (!Repositorio.consultaFuncionario(f)) {
//            System.out.println("Funcionário não cadstrado.");
//            return;
//        }
// valida funcionário
//</editor-fold>
        menuPrincipal();
        while (sc.hasNext()) {
            s = sc.next();
            try {
                int eInt = Integer.parseInt(s);
            } catch (Exception ex) {
                s = "-1";
            }

            if (Integer.parseInt(s) == 0) {
                break;
            } else if (Integer.parseInt(s) == 1) {
// efetuar reserva
                aula = false;
                obterDadosReserva();
                String assunto = "";
                System.out.print("Digite o assunto da reserva:\n");
                Scanner as = new Scanner(System.in);
                assunto = as.nextLine();
                reserva.setAssunto(assunto);
                reserva.setAula(aula);
                try {
                    registradorReserva.setReserva(reserva);
                    registradorReserva.gerarReserva();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (Integer.parseInt(s) == 2) {
// cancelar reserva 
                obterDadosReserva();
                try {
                    registradorReserva.setReserva(reserva);
                    registradorReserva.cancelarReserva();
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
            } else if (Integer.parseInt(s) == 8) {
// cadastrar equipamento
            } else if (Integer.parseInt(s) == 9) {
// cadastrar aula
                aula = true;
                obterDadosReserva();
                reserva.setAula(aula);
                reserva.setAssunto("Aula");
                try {
                    registradorReserva.setReserva(reserva);
                    registradorReserva.gerarReservaAula(dataFimSemestre, diaSemana);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Opção inválida");
            }
            menuPrincipal();

        }

        sc.close(); //Encerra o programa

//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
//Date data = formato.parse("23/11/2015");
    }

    public void menuPrincipal() {
        System.out.print("Selecione uma das opções abaixo:\n");
        System.out.print("1 - Efetuar reserva\n");
        System.out.print("2 - Cancelar reserva\n");
        System.out.print("3 - Reserva de Equipamento...\n");
        System.out.print("4 - Salas livres\n");
        System.out.print("5 - Salas ocupadas\n");
        System.out.print("6 - Reservas ativas\n");
        System.out.print("7 - Reservas inativas\n");
        System.out.print("8 - Cadastrar equipamentos\n");
        System.out.print("9 - Cadastrar aulas\n");
        System.out.print("0 - Sair\n");

    }

    public void subMenu() {
        System.out.print("Selecione uma das opções abaixo:\n");
        System.out.print("1 - Reserva equipamento\n");
        System.out.print("2 - Cancela reserva de equipamento\n");
        System.out.print("0 - Retorna ao menu principal\n");

    }

    public void obterDadosReserva() {
        String s;

        salaReuniao = new SalaReuniao();
//        reserva = new Reserva();
        System.out.print("Digite um numero maior que zero para o predio:\n");
        while (sc.hasNext()) {
            s = sc.next();
            try {
                codigoPredio = Integer.parseInt(s);
                if (codigoPredio > 0) {
                    break;
                }
            } catch (Exception ex) {
            }
            System.out.print("Valor digitado inválido\n");
            System.out.print("Digite um numero maior que zero para o predio:\n");
        }
        System.out.print("Digite um numero maior que zero para a sala:\n");
        while (sc.hasNext()) {
            s = sc.next();
            try {
                codigoSalaReuniao = Integer.parseInt(s);
                if (codigoSalaReuniao > 0) {
                    break;
                }
            } catch (Exception ex) {
            }
            System.out.print("Valor digitado inválido\n");
            System.out.print("Digite um numero maior que zero para a sala:\n");
        }
        if (aula == false) {
            System.out.print("Digite a data da reserva no formato 'aaaa-mm-dd':\n");
        } else {
            System.out.print("Digite a data início do semestre no formato 'aaaa-mm-dd':\n");
        }
        while (sc.hasNext()) {
            s = sc.next();
            try {
                dataReserva = LocalDate.parse(s);
                if (dataReserva.compareTo(LocalDate.now()) >= 0) {
                    break;
                }
                if (aula == true) {
                    break;
                }
            } catch (Exception ex) {
            }
            System.out.print("Valor digitado inválido\n");
            if (aula == false) {
                System.out.print("Digite a data da reserva no formato 'aaaa-mm-dd':\n");
            } else {
                System.out.print("Digite a data início do semestre no formato 'aaaa-mm-dd':\n");
            }
        }
        if (aula == true) {
            System.out.print("Digite a data fim do semestre no formato 'aaaa-mm-dd':\n");
            while (sc.hasNext()) {
                s = sc.next();
                try {
                    dataFimSemestre = LocalDate.parse(s);
                    if (dataFimSemestre.compareTo(dataReserva) > 0
                            || dataFimSemestre.compareTo(dataReserva) == 0) {
                        break;
                    }
                } catch (Exception ex) {
                }
                System.out.print("Valor digitado inválido\n");
                System.out.print("Digite a data fim do semestre no formato 'aaaa-mm-dd':\n");
            }
            System.out.print("Digite o dia da semana da aula:\n");
            System.out.print("1-segunda 2-terca 3-quarta 4-quinta 5-sexta 6-sábado:\n");
            while (sc.hasNext()) {
                s = sc.next();
                try {
                    diaSemana = Integer.parseInt(s);
                    if (diaSemana > 0 && diaSemana < 7) {
                        break;
                    }
                } catch (Exception ex) {
                }
                System.out.print("Valor digitado inválido\n");
                System.out.print("Digite o dia da semana da aula:\n");
                System.out.print("1-segunda 2-terca 3-quarta 4-quinta 5-sexta 6-sábado:\n");
            }

        }

        System.out.print("Digite a hora de início no formato 'hh:mm':\n");
        while (sc.hasNext()) {
            s = sc.next();
            try {
                horaInicio = LocalTime.parse(s);
                break;
            } catch (Exception ex) {
            }
            System.out.print("Valor digitado inválido\n");
            System.out.print("Digite a hora de início no formato 'hh:mm':\n");
        }
        System.out.print("Digite a hora final no formato 'hh:mm':\n");
        while (sc.hasNext()) {
            s = sc.next();
            try {
                horaFim = LocalTime.parse(s);
                if (horaFim.compareTo(horaInicio) > 0) {
                    break;
                }
            } catch (Exception ex) {
            }
            System.out.print("Valor digitado inválido\n");
            System.out.print("Digite a hora final no formato 'hh:mm':\n");
        }
        salaReuniao.setCodigo(codigoSalaReuniao);
        salaReuniao.getPredio().setCodigo(codigoPredio);
        salaReuniao.getPredio().setCampus(campus);
//        salaReuniao.getPredio().getCampus().setCodigo(campus.getCodigo());

        reserva.setSalaReuniao(salaReuniao);
        reserva.setDataReserva(dataReserva);
        reserva.setHoraInicio(horaInicio);
        reserva.setHoraFim(horaFim);
        reserva.setFuncionario(funcionario);
    }

    public void registarEquipamentos() {
        String s;
        if (!Repositorio.consultaReserva(reserva)) {
            System.out.println("Reserva não cadastrada.");
            return;
        }

        subMenu();
        while (sc.hasNext()) {
            s = sc.next();
            try {
                int eInt = Integer.parseInt(s);
            } catch (Exception ex) {
                s = "-1";
            }
            if (Integer.parseInt(s) == 1) {
                try {
                    obterDadosEquipamento();
                    registradorReserva.gerarItemEquipamento();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (Integer.parseInt(s) == 2) {
                try {
                    obterDadosEquipamento();
                    registradorReserva.cancelarItemEquipamento();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Opção inválida");
            }
            subMenu();
        }

    }

    public void obterDadosEquipamento() {
        String s;
//        itemEquipamento = new ItemEquipamento();
        System.out.print("Digite o código do equipamento:\n");
        s = sc.next();
        codigoEquipamento = Integer.parseInt(s);
        equipamento.setCodigo(codigoEquipamento);
        equipamento.getCampus().setCodigo(campus.getCodigo());
        itemEquipamento.setEquipamento(equipamento);
        itemEquipamento.setReserva(reserva);
        try {
            registradorReserva.setItemEquipamento(itemEquipamento);
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

}
