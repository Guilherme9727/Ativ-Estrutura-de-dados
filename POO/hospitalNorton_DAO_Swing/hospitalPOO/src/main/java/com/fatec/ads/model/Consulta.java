package com.fatec.ads.model;

public class Consulta {
    private int id;
    private String data;
    private String hora;
    private String motivo;
    private String historico;
    private Medico medico;
    private Paciente paciente;

    public Consulta() {
        this.data = "indefinido";
        this.hora = "indefinido";
        this.motivo = "indefinido";
        this.historico = "indefinido";
    }

    public Consulta(String data, String hora, String motivo, String historico,
                    Medico medico, Paciente paciente) {
        this.data = data;
        this.hora = hora;
        this.motivo = motivo;
        this.historico = historico;
        this.medico = medico;
        this.paciente = paciente;
    }

    public int getId()                  { return id; }
    public void setId(int id)           { this.id = id; }
    public String getData()             { return data; }
    public void setData(String data)    { this.data = data; }
    public String getHora()             { return hora; }
    public void setHora(String hora)    { this.hora = hora; }
    public String getMotivo()           { return motivo; }
    public void setMotivo(String m)     { this.motivo = m; }
    public String getHistorico()        { return historico; }
    public void setHistorico(String h)  { this.historico = h; }
    public Medico getMedico()           { return medico; }
    public void setMedico(Medico m)     { this.medico = m; }
    public Paciente getPaciente()       { return paciente; }
    public void setPaciente(Paciente p) { this.paciente = p; }

    @Override
    public String toString() {
        String med = medico != null ? medico.getNome() : "N/A";
        String pac = paciente != null ? paciente.getNome() : "N/A";
        return "[" + id + "] " + data + " " + hora + " | " + pac + " c/ Dr. " + med + " | " + motivo;
    }
}
