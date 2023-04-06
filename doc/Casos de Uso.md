# Casos de Uso BookARoom

## Descrição
Este documento apresenta uma lista de casos de uso simplificados para o sistema **BookARoom**, desenvolvido para controlar e facilitar a tarefa de reserva de salas para reuniões nos campi do Instituto Federal do Norte de Minas Gerais. Os casos de uso abrangem desde a visualização de salas livres e ocupadas até a reserva de salas e associação de equipamentos às reservas. Para garantir a prioridade de utilização das salas para aulas, o sistema não permitirá que reservas de reuniões sejam feitas em horários de aulas.


## Casos de uso

**UC1 - Realizar reserva de sala**: Permite que o usuário selecione uma sala livre com base numa data e horário especificados para realizar a reserva. É um caso de uso fundamental para o sistema, pois permite que os usuários encontrem e reservem as salas para suas atividades.

**UC2 - Visualizar  salas ocupadas**:  Permite que o usuário solicite uma lista de ocupação das salas, que pode ser apresentada por dia, semana ou mês. Esse caso de uso é importante para que os usuários possam ter uma visão geral da disponibilidade das salas antes de realizar uma reserva.

**UC3 - Visualizar  salas livres**:  Permite que o usuário solicite uma lista de  salas livres, que pode ser apresentada por dia, semana ou mês. Esse caso de uso é importante para que os usuários possam ter uma visão geral da disponibilidade das salas antes de realizar uma reserva.
    
**UC4 - Reservar equipamentos**: Permite que o usuário realize uma reserva de um equipamento para reserva de sala (**UC1**) determinada . Esse caso de uso é importante para garantir que os usuários possam solicitar equipamentos necessários para suas atividades durante a reserva de uma sala.

**UC5 - Visualizar  equipamento em uso**:  Permite que o usuário solicite uma lista de ocupação dos equipamentos, que pode ser apresentada por dia, semana ou mês. Esse caso de uso é importante para que os usuários possam ter uma visão geral da disponibilidade dos equipamentos antes de realizar uma reserva.

**UC6 - Visualizar  equipamentos livres**:  Permite que o usuário solicite uma lista de equipamentos disponíveis, que pode ser apresentada por dia, semana ou mês. Esse caso de uso é importante para que os usuários possam ter uma visão geral da disponibilidade dos equipamentos antes de realizar uma reserva.

**UC7 - Cancelar reserva de equipamento**: Permite que o usuário cancela uma reserva de um equipamento previamente feita. Esse caso de uso é importante para garantir que os usuários possam liberar um equipamento que não será mais utilizado, permitindo que outros usuários possam reservá-lo.

**UC8 - Cancelar reserva de sala**: Permite que o usuário cancela uma reserva de sala previamente feita. Esse caso de uso é importante para garantir que os usuários possam liberar uma sala que não será mais utilizada, permitindo que outros usuários possam reservá-la. **(Chama UC7 antes de executar caso tenha um equipamento)**

**UC9 - Visualizar histórico de reservas**: Permite que o usuário visualize o histórico de suas reservas, incluindo as ativas e inativas. Esse caso de uso é importante para que os usuários possam acompanhar suas reservas passadas e futuras.


**UC10 - Visualizar relatórios de uso de salas e equipamentos**: Este caso de uso descreve o processo de visualização de relatórios de uso de salas e equipamentos, permitindo que os administradores vejam como as salas e equipamentos estão sendo utilizados em diferentes períodos. Esse caso de uso é importante para permitir que os administradores avaliem a eficiência do uso das salas e equipamentos, identificando áreas de melhoria e tomando decisões informadas sobre como alocar recursos de forma mais eficiente.

