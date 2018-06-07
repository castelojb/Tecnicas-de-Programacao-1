package visao.janela.central.observer;

import visao.janela.PainelInformacoes;
import visao.janela.central.janela.JanelaGraficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Graficos implements ActionListener{
    PainelInformacoes informacoes;
    String nome;

    public Graficos(PainelInformacoes informacoes, String nome){
        this.informacoes = informacoes;
        this.nome = nome;

    }

    public void actionPerformed(ActionEvent evento){
        JanelaGraficos janela = new JanelaGraficos(informacoes, nome);

    }

}
