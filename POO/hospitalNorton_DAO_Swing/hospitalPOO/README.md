# Hospital Norton - POO + DAO + Swing CRUD

## Estrutura do Projeto

```
com.fatec.ads
├── model/
│   ├── Funcionario.java   (classe abstrata base)
│   ├── Medico.java        (extends Funcionario)
│   ├── Paciente.java
│   └── Consulta.java
├── dao/
│   ├── ConnectionFactory.java  (SQLite + criação automática das tabelas)
│   ├── MedicoDAO.java          (CRUD completo)
│   ├── PacienteDAO.java        (CRUD completo)
│   └── ConsultaDAO.java        (CRUD com JOIN médico/paciente)
├── view/
│   ├── MainFrame.java    (JFrame principal com JTabbedPane)
│   ├── MedicoView.java   (tela CRUD Médico)
│   ├── PacienteView.java (tela CRUD Paciente)
│   └── ConsultaView.java (tela CRUD Consulta)
└── App.java              (main - inicia Swing EDT)
```

## Como Executar

### Opção 1 — Maven (recomendado)
```bash
mvn compile exec:java -Dexec.mainClass=com.fatec.ads.App
```

### Opção 2 — Javac direto
```bash
# Compilar
javac -cp lib/sqlite-jdbc.jar -d target/classes $(find src -name "*.java")

# Executar
java -cp "target/classes:lib/sqlite-jdbc.jar" com.fatec.ads.App
# Windows:
java -cp "target/classes;lib/sqlite-jdbc.jar" com.fatec.ads.App
```

## Banco de Dados
- O arquivo `hospital_norton.db` é criado automaticamente na pasta onde o programa é executado.
- As tabelas (`medico`, `paciente`, `consulta`) são criadas automaticamente na primeira execução.

## Funcionalidades
- **Médicos:** Cadastrar, alterar, excluir, buscar por ID, listar todos
- **Pacientes:** Cadastrar, alterar, excluir, buscar por ID, listar todos
- **Consultas:** Cadastrar, alterar, excluir, listar todas (com JOIN exibindo nome do médico e paciente)
