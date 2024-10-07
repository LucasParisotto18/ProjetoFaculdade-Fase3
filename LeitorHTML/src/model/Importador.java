/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class Importador {

    private PilhaVetorEncadeada pilhaTags;
    private PilhaVetorEncadeada pilhaTags2;
    private PilhaVetorEncadeada<Tag> pilhaEndTags;
    private PilhaVetorEncadeada<Tag> pilhaEndTags2;
    private PilhaVetorEncadeada<Tag> pilhaAgrupada;
    //File arquivo;

    public Importador() {
        this.pilhaTags = new PilhaVetorEncadeada<>();
        this.pilhaTags2 = new PilhaVetorEncadeada<>();
        this.pilhaEndTags = new PilhaVetorEncadeada<>();
        this.pilhaEndTags2 = new PilhaVetorEncadeada<>();
        this.pilhaAgrupada = new PilhaVetorEncadeada<>();
    }

    /**
     *
     *
     * @param arquivo do tipo file que deve ser lido pelo metodo
     * @throws FileNotFoundException Exeção para diretório não encontrado
     *
     */
    public void carregarDados(File arquivo) throws FileNotFoundException {
        //C:\Users\Lucas\Desktop\Faculdade\Fase3\HTML.txt
        try (Scanner sc = new Scanner(arquivo, "UTF-8")) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] dados = linha.split("\n");
                for (String dado : dados) {
                    pilhaTags.SoTagHTML(linha, pilhaTags);
                    pilhaTags2.SoTagHTML(linha, pilhaTags2);                    
                }
            }
            pilhaEndTags = pilhaTags.PilhacomEndTags(pilhaEndTags);
            pilhaEndTags2 = pilhaTags2.PilhacomEndTags(pilhaEndTags2);
            pilhaAgrupada = pilhaTags.agruparTags();
          
            /*  
            System.out.println("Tags:");
            System.out.println(pilhaTags.toString());
            System.out.println("End Tags:");
            System.out.println(pilhaEndTags.toString());
            System.out.println("Agrupada");
            System.out.println(pilhaAgrupada.toString());

            try {
                boolean validacao = pilhaTags.validarTagsFechamento(pilhaEndTags);
                System.out.println("Todas as tags estão corretamente fechadas: " + validacao);
            } catch (RuntimeException e) {
                System.out.println("Erro de validação: " + e.getMessage());
            }

            try {
                boolean validacao = pilhaTags2.validarTagsAbertura(pilhaEndTags2, pilhaTags2);
                System.out.println("Todas as tags estão abertas corretamente: " + validacao);
            } catch (RuntimeException e) {
                System.out.println("Erro de validação: " + e.getMessage());
            } */

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Erro ao achar diretorio.(Método carregarDados)" + e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar dados do arquivo. (Método carregarDados)", e);
        }

    }

    public PilhaVetorEncadeada getPilhaTags() {
        return pilhaTags;
    }

    public PilhaVetorEncadeada<Tag> getPilhaEndTags() {
        return pilhaEndTags;
    }

    public PilhaVetorEncadeada<Tag> getPilhaAgrupada() {
        return pilhaAgrupada;
    }

    public PilhaVetorEncadeada getPilhaTags2() {
        return pilhaTags2;
    }

    public PilhaVetorEncadeada<Tag> getPilhaEndTags2() {
        return pilhaEndTags2;
    }
    

}
