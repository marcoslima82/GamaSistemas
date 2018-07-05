/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.bean;

/**
 *
 * @author Marcos Lima marcoslima82@gmail.com
 */
public class FuncionariosBean {

    private String nome;
    private String hora;
    private String batida_tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getBatida_tipo() {
        return batida_tipo;
    }

    public void setBatida_tipo(String batida_tipo) {
        this.batida_tipo = batida_tipo;
    }
}
