package com.mycompany.bookaroom.cadastro;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class Endereco {

    private int codigo;
    private Campus campus;
    private String logradouro;
    private int numero;
    private String bairro;
    private long cep;
    private String cidade;
    private String estado;

//<editor-fold defaultstate="collapsed" desc="construtores">
    public Endereco() {

    }

    public Endereco(Endereco e) {
        this.setCodigo(e.getCodigo());
//        this.setCampus(new Campus(e.getCampus()));
        this.setCampus(new Campus());
        this.getCampus().setCodigo(e.getCampus().getCodigo());
        this.getCampus().setNome(e.getCampus().getNome());
        this.setLogradouro(e.getLogradouro());
        this.setNumero(e.getNumero());
        this.setBairro(e.getBairro());
        this.setCep(e.getCep());
        this.setCidade(e.getCidade());
        this.setEstado(e.getEstado());
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public long getCep() {
        return cep;
    }

    public void setCep(long cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//</editor-fold>
}
