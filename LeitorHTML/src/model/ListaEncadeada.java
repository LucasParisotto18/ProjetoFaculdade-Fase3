/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lucas
 */
public class ListaEncadeada<T> {

    private NoLista<T> primeiro;

    public void inserir(T info) {
        NoLista<T> novo = new NoLista();
        novo.setInfo(info);
        novo.setProximo(primeiro);
        this.primeiro = novo;
    }

    public void exibir() {
        NoLista<T> atual = primeiro;
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getProximo();
        }
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public NoLista<T> buscar(T valor) {
        NoLista<T> atual = primeiro;
        while (atual != null) {
            if (atual.getInfo().equals(valor)) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public boolean contem(T valor) {
        NoLista<T> atual = primeiro;
        while (atual != null) {
            if (atual.getInfo().equals(valor)) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    public void retirar(T valor) {
        NoLista<T> anterior = null;
        NoLista<T> atual = primeiro;

        while (atual != null && !atual.getInfo().equals(valor)) {
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual != null) {
            if (atual == primeiro) {
                this.primeiro = atual.getProximo();
            } else {
                anterior.setProximo(atual.getProximo());
            }
        }
    }

    public void retirar(String valor) {
        NoLista<T> anterior = null;
        NoLista<T> atual = primeiro;

        while (atual != null && !atual.getInfo().equals(valor)) {
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual != null) {
            if (atual == primeiro) {
                this.primeiro = atual.getProximo();
            } else {
                anterior.setProximo(atual.getProximo());
            }
        }
    }

    public NoLista<T> getPrimeiro() {
        return primeiro;
    }

    public int obterComprimento() {
        int qtdeNos = 0;

        NoLista<T> atual = getPrimeiro();

        while (atual != null) {
            qtdeNos++;
            atual = atual.getProximo();
        }

        return qtdeNos;
    }

    public NoLista<T> obterNo(int posicao) {
        if ((posicao < 0) || (posicao > obterComprimento() - 1)) {
            throw new IndexOutOfBoundsException("Indice não existe");
        }

        NoLista<T> p = getPrimeiro();
        for (int i = 0; i < posicao; i++) {
            p = p.getProximo();
        }

        return p;
    }

    public void liberar() {
        NoLista<T> atual = primeiro;
        while (atual != null) {
            NoLista<T> proximo = atual.getProximo();
            atual.setProximo(null);

            atual = proximo;
        }
        primeiro = null;
    }

    @Override
    public String toString() {
        String resultado = "";
        NoLista<T> p = primeiro;
        while (p != null) {
            resultado += p.getInfo();
            if (p.getProximo() != null) {
                resultado += "\n";
            }
            p = p.getProximo();
        }
        return resultado;
    }
}
